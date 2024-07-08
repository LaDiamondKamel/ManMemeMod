package com.mememan.mememanmod.util;

import com.mememan.mememanmod.datagen.MMMLanguageProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;

public final class MiscUtil {

    private MiscUtil() {
        throw new IllegalAccessError("Attempted to construct a Utility Class!");
    }

    /**
     * Safely attempts to insert a {@link String} into another {@link String} at the specified index. Referenced from
     * {@code https://www.geeksforgeeks.org/insert-a-string-into-another-string-in-java/}.
     *
     * @param originalString The original {@link String} to modify.
     * @param stringToBeInserted The {@link String} to insert into the specified original {@link String}.
     * @param insertionIndex The index at which to insert the {@link String} to be inserted. Appends to the end of the original {@link String}
     *                       if the insertion index is larger than or equal to the length of the original {@link String}.
     *
     * @return A new {@link String} with the contents of the specified insertion {@link String} inserted at the specified index.
     */
    public static String insertStringAt(String originalString, String stringToBeInserted, int insertionIndex) {
        String moddedString = new String();

        if (insertionIndex >= originalString.length()) return originalString.concat(stringToBeInserted);

        for (int i = 0; i < originalString.length(); i++) {
            moddedString += originalString.charAt(i);

            if (i == insertionIndex) moddedString += stringToBeInserted;
        }

        return moddedString;
    }

    /**
     * Creates and adds a {@link MutableComponent} containing {@link TranslatableContents} to {@link MMMLanguageProvider#MANUAL_TRANSLATIONS}.
     *
     * @param translatableComponent The {@link MutableComponent} (usually created using {@link Component#translatable(String)} or its other method
     *                              variants) containing the translation key to translate.
     * @param defaultTranslation The default unlocalized value of the specified translation key passed in for data generation.
     *
     * @return {@code translatableComponent} after adding it to {@link MMMLanguageProvider#MANUAL_TRANSLATIONS} for data generation.
     */
    public static MutableComponent translatableDefault(MutableComponent translatableComponent, String defaultTranslation) {
        MMMLanguageProvider.addManualTranslation(ChatFormatting.stripFormatting(translatableComponent.toString()), defaultTranslation);
        return translatableComponent;
    }
}
