package com.mememan.mememanmod.datagen;

import com.mememan.mememanmod.ManMemeMod;
import com.mememan.mememanmod.common.registry.MMMBlocks;
import com.mememan.mememanmod.common.registry.MMMItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MMMItemModelProvider extends ItemModelProvider {
    private static final List<RegistryObject<Item>> exemptions = new ArrayList<>();
    private static final ResourceLocation ITEM_GENERATED = new ResourceLocation("item/generated");

    public MMMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ManMemeMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return ManMemeMod.MOD_NAME.concat(": Item Models");
    }

    @Override
    protected void registerModels() {
        exemptions.add(MMMItems.MAN_MEME_MALLET);
        registerBlockItemModels();
        registerItemModels();
    }

    private void registerItemModels() {
        
        final ModelFile parentGenerated = getExistingFile(mcLoc("item/generated"));
        final ModelFile.ExistingModelFile parentHandheld = getExistingFile(mcLoc("item/handheld"));

        MMMItems.ITEMS.getEntries().forEach(itemEntry -> {
            String itemRegName = itemEntry.getId().getPath();

            ManMemeMod.LOGGER.debug("[Generating Item Model]: " + itemRegName);

            if (itemEntry.getId().getPath().contains("_spawn_egg")) {
                getBuilder(itemEntry.getId().getPath()).parent(parentGenerated).texture("layer0", ItemModelProvider.ITEM_FOLDER + "/spawn_eggs/" + itemRegName.replaceAll("_spawn_egg", ""));
            } else if (itemEntry.getId().getPath().contains("boat")) {
                getBuilder(itemEntry.getId().getPath()).parent(parentGenerated).texture("layer0", ItemModelProvider.ITEM_FOLDER + "/boats/" + itemRegName.replaceAll("_boat", ""));
            } else {
                if (existingFileHelper.exists(getItemResourceLocation(itemRegName), TEXTURE) || existingFileHelper.exists(getItemResourceLocation(itemRegName), MODEL)) getBuilder(itemEntry.getId().getPath()).parent(itemEntry.get().getMaxDamage(ItemStack.EMPTY) > 0 && !(itemEntry.get() instanceof ArmorItem) ? parentHandheld : parentGenerated).texture("layer0", ItemModelProvider.ITEM_FOLDER + "/" + itemRegName);;
            }
        });
    }

    private void registerBlockItemModels() {

        MMMBlocks.ITEM_BLOCKS.getEntries().forEach(blockItemRegEntry -> {
            String blockItemRegName = blockItemRegEntry.getId().getPath();
            BlockItem blockItemEntry = (BlockItem) blockItemRegEntry.get();
            Block blockEntry = blockItemEntry.getBlock();

            ManMemeMod.LOGGER.debug("[Generating BlockItem Model]: " + blockItemRegName);

            if (blockItemRegName.contains("_wall")) {
                withExistingParent(blockItemRegName, getBlockResourceLocation(blockItemRegName + "_inventory"));
            } else if (blockItemRegName.contains("_trapdoor")) {
                withExistingParent(blockItemRegName, getBlockResourceLocation(blockItemRegName + "_bottom"));
            } else if (blockItemRegName.contains("_door")) {
                singleTextureLayer0(blockItemRegName, ITEM_GENERATED, getItemResourceLocation(blockItemRegName));
            } else if (blockEntry instanceof SaplingBlock || blockEntry instanceof FlowerBlock) {
                singleTextureLayer0(blockItemRegName, ITEM_GENERATED, getBlockResourceLocation(blockItemRegName));
            } else if (blockEntry instanceof FenceBlock || blockEntry instanceof ButtonBlock) {
                withExistingParent(blockItemRegName, getBlockResourceLocation(blockItemRegName + "_inventory"));
            } else {
                if (existingFileHelper.exists(getBlockResourceLocation(blockItemRegName), MODEL) || existingFileHelper.exists(getItemResourceLocation(blockItemRegName), MODEL)) withExistingParent(blockItemRegName, getBlockResourceLocation(blockItemRegName));
            }
        });
    }

    public void defaultItem(Collection<RegistryObject<Item>> items) {
        for (RegistryObject<Item> item : items) {

            if (exemptions.contains(item)) {
                return;
            }

            String name = item.getId().getPath();
            Item getItem = item.get();
            ResourceLocation datagenLoc = ManMemeMod.prefix("item/" + name);
            ModelFile.ExistingModelFile modelType = getItem instanceof DiggerItem || getItem instanceof SwordItem ? getMcLoc("item/handheld") : getMcLoc("item/generated");

            if (getItem instanceof BlockItem) {
                return;
            }

            if (existingFileHelper.exists(datagenLoc, TEXTURE) || !existingFileHelper.exists(datagenLoc, MODEL)) {
                this.getBuilder(name).parent(modelType).texture("layer0", ITEM_FOLDER + "/" + name);
            }
        }
    }


    protected static ResourceLocation getBlockResourceLocation(String name) {
        return getResourceLocation("block/" + name);
    }

    protected static ResourceLocation getItemResourceLocation(String name) {
        return getResourceLocation("item/" + name);
    }

    protected static ResourceLocation getResourceLocation(String path) {
        return ManMemeMod.prefix(path);
    }

    public ModelFile.ExistingModelFile getMcLoc(String mcModel) {
        return getExistingFile(mcLoc(mcModel));
    }

    public ModelFile.ExistingModelFile getMMLoc(String mmModel) {
        return getExistingFile(mmLoc(mmModel));
    }

    public ResourceLocation mmLoc(String name) {
        return ManMemeMod.prefix(name);
    }

    protected ItemModelBuilder singleTextureLayer0(String name, ResourceLocation parent, ResourceLocation texture) {
        return singleTexture(name, parent, "layer0", texture);
    }
}
