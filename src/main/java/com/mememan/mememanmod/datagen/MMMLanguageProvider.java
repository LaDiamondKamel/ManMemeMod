package com.mememan.mememanmod.datagen;

import com.google.common.collect.LinkedHashMultimap;
import com.mememan.mememanmod.ManMemeMod;
import com.mememan.mememanmod.common.registry.MMMBlocks;
import com.mememan.mememanmod.common.registry.MMMCreativeModeTabs;
import com.mememan.mememanmod.common.registry.MMMItems;
import com.mememan.mememanmod.util.MiscUtil;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.List;
import java.util.stream.Collectors;

public class MMMLanguageProvider extends LanguageProvider {
    private static final ObjectArrayList<String> DEFAULT_SEPARATORS = ObjectArrayList.of("Of", "And");
    private static final Object2ObjectOpenHashMap<String, String> MANUAL_TRANSLATIONS = new Object2ObjectOpenHashMap<String, String>();
    private static final LinkedHashMultimap<String, String> MANUAL_INSERTIONS = LinkedHashMultimap.create();

    public MMMLanguageProvider(PackOutput output) {
        super(output, ManMemeMod.MODID, "en_us");
    }

    @Override
    public String getName() {
        return ManMemeMod.MOD_NAME.concat(": Language Provider (en_us)");
    }

    public static void addManualTranslation(String localisedKey, String translationValue) {
        MANUAL_INSERTIONS.put(localisedKey, translationValue);
    }

    private String handleInput(String displayName) {
        ObjectArrayList<String> finalizedList = new ObjectArrayList<String>(1);

        MANUAL_INSERTIONS.asMap().forEach((curDisplayName, matchersAndInsertion) -> {
            if (!displayName.equalsIgnoreCase(curDisplayName)) return;

            ObjectArrayList<String> copiedMatchersAndInsertions = new ObjectArrayList<String>(matchersAndInsertion);
            ObjectArrayList<String> prunedMatchersAndInsertion = copiedMatchersAndInsertions.stream()
                    .filter(curString -> copiedMatchersAndInsertions.indexOf(curString) < 2)
                    .collect(Collectors.toCollection(ObjectArrayList::new));

            if (curDisplayName.indexOf(prunedMatchersAndInsertion.get(0)) == -1) return;

            int insertionLocation = curDisplayName.indexOf(prunedMatchersAndInsertion.get(0)) + prunedMatchersAndInsertion.get(0).length() - 1;

            curDisplayName = MiscUtil.insertStringAt(curDisplayName, prunedMatchersAndInsertion.get(1), insertionLocation);

            finalizedList.add(curDisplayName);
            ManMemeMod.LOGGER.debug("[Inserted Manual String]: " + displayName + " -> " + curDisplayName);
        });

        return finalizedList.isEmpty() ? displayName : finalizedList.get(0);
    }

    // https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
    private static String formatString(String input, List<String> separators) { //TODO Refactor
        char[] charSet = input.toLowerCase().toCharArray();
        boolean found = false;

        for (int i = 0; i < charSet.length - 1; i++) {
            if (!found && Character.isLetter(charSet[i])) {
                charSet[i] = Character.toUpperCase(charSet[i]);
                found = true;
            } else if (Character.isWhitespace(charSet[i]) || charSet[i] == '.' || charSet[i] == '_') found = false;
        }

        String baseResult = String.valueOf(charSet);

        for (String lcw : DEFAULT_SEPARATORS) if (baseResult.contains(lcw)) baseResult = baseResult.replaceAll(lcw, lcw.toLowerCase());

        return baseResult;
    }

    private static String formatString(String input) {
        return formatString(input, DEFAULT_SEPARATORS);
    }

    private String getTranslatedRegistryName(String registryName, List<String> separators) {
        if (registryName.isBlank() || registryName.isEmpty() || registryName == null) return registryName;
        if (!registryName.contains(".")) return registryName;

        String regNameTemp = registryName;
        String formattedName = formatString(regNameTemp, separators);
        String displayName = formattedName.substring(formattedName.lastIndexOf(".") + 1).replaceAll("_", " ");

        return displayName;
    }

    private String getTranslatedRegistryName(String registryName) {
        return getTranslatedRegistryName(registryName, DEFAULT_SEPARATORS);
    }

    private void localizeGeneralRegistryName(String registryName, String translatedName, List<String> toTrim) {
        if (toTrim != null && !toTrim.isEmpty()) {
            for (int i = 0; i < toTrim.size(); i++) {
                String curTrim = toTrim.get(i);

                translatedName = translatedName.replaceFirst(curTrim, "");
            }
        }

        if (!MANUAL_TRANSLATIONS.containsKey(registryName)) add(registryName, handleInput(translatedName));
    }

    private void localizeGeneralRegistryName(String registryName, List<String> separators, List<String> toTrim) {
        String translatedRegName = getTranslatedRegistryName(registryName, separators);

        if (toTrim != null && !toTrim.isEmpty()) {
            for (int i = 0; i < toTrim.size(); i++) {
                String curTrim = toTrim.get(i);

                translatedRegName = translatedRegName.replaceFirst(curTrim, "");
            }
        }

        if (!MANUAL_TRANSLATIONS.containsKey(registryName)) add(registryName, handleInput(translatedRegName));
    }

    private void localizeGeneralRegistryName(String registryName, String translatedName) {
        localizeGeneralRegistryName(registryName, translatedName, List.of());
    }

    private void localizeGeneralRegistryName(String registryName, List<String> toTrim) {
        localizeGeneralRegistryName(registryName, DEFAULT_SEPARATORS, toTrim);
    }

    private void localizeGeneralRegistryName(String registryName) {
        localizeGeneralRegistryName(registryName, List.of());
    }

    private void translateBlocks() {
        MMMBlocks.BLOCKS.getEntries().forEach(blockRegEntry -> {
            Block blockEntry = blockRegEntry.get();
            String blockRegName = blockEntry.getDescriptionId();

            ManMemeMod.LOGGER.debug("[Currently Translating Block]: " + blockRegName + " -> " + getTranslatedRegistryName(blockRegName));

            handleManualBlockTranslations();

            if (blockRegName.endsWith("_block")) localizeGeneralRegistryName(blockRegName, "Block of " + getTranslatedRegistryName(blockRegName).substring(0, getTranslatedRegistryName(blockRegName).lastIndexOf(" Block")));
            else localizeGeneralRegistryName(blockRegName);
        });
    }

    private void translateCreativeModeTabs() {
        MMMCreativeModeTabs.CREATIVE_MODE_TABS.getEntries().forEach(tabRegEntry -> {
            CreativeModeTab tabEntry = tabRegEntry.get();
            String tabRegName = tabEntry.getDisplayName().toString();
            int endCharIndex = tabRegName.indexOf("',");
            tabRegName = tabRegName.substring(17, endCharIndex);

            ManMemeMod.LOGGER.debug("[Currently Translating Creative Mode Tab]: " + tabRegName + " -> " + getTranslatedRegistryName(tabRegName));

            MANUAL_INSERTIONS.putAll(getTranslatedRegistryName(tabRegName), ObjectArrayList.of("Eyespy", ":"));

            localizeGeneralRegistryName(tabRegName);
        });
    }

    private void translateItems() {
        MMMItems.ITEMS.getEntries().forEach(itemRegEntry -> {
            Item itemEntry = itemRegEntry.get();
            String itemRegName = itemEntry.getDescriptionId();

            ManMemeMod.LOGGER.debug("[Currently Translating Item]: " + itemRegName + " -> " + getTranslatedRegistryName(itemRegName));

            localizeGeneralRegistryName(itemRegName);
        });
    }

    protected void handleManualBlockTranslations() {

    }

    private void handleManualTranslations() {
        MANUAL_TRANSLATIONS.forEach((translationKey, translationValue) -> {
            if ((translationKey == null || translationValue == null) || translationKey.isEmpty() || translationKey.isBlank() || translationValue.isEmpty() || translationValue.isBlank()) return;

            add(translationKey, translationValue);
        });
    }

    @Override
    protected void addTranslations() {
        translateBlocks();
        translateCreativeModeTabs();
        translateItems();

        handleManualTranslations();
    }
}

