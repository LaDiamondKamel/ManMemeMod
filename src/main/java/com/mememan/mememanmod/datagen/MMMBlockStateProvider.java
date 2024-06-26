package com.mememan.mememanmod.datagen;

import com.mememan.mememanmod.ManMemeMod;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class MMMBlockStateProvider extends BlockStateProvider {
    public MMMBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ManMemeMod.MODID, exFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return ManMemeMod.MOD_NAME.concat(": BlockState Provider");
    }

    @Override
    protected void registerStatesAndModels() {//TODO Automate
    }

    protected void farmland(FarmBlock farmBlock) {
        ModelFile normalModel = models().getExistingFile(blockTexture(farmBlock));
        ModelFile moistModel = models().getExistingFile(ManMemeMod.prefix(blockTexture(farmBlock).getPath() + "_moist"));

        getVariantBuilder(farmBlock).forAllStatesExcept(targetState -> ConfiguredModel.builder().modelFile(targetState.getValue(FarmBlock.MOISTURE) == 7 ? moistModel : normalModel).build());
    }

    protected void topBlock(Block targetBlock) {
        simpleBlock(targetBlock, new ConfiguredModel(models().cubeTop(ForgeRegistries.BLOCKS.getKey(targetBlock).getPath(), ManMemeMod.prefix("block/" + ForgeRegistries.BLOCKS.getKey(targetBlock).getPath() + "_side"), ManMemeMod.prefix("block/" + ForgeRegistries.BLOCKS.getKey(targetBlock).getPath() + "_top"))));
    }
}

