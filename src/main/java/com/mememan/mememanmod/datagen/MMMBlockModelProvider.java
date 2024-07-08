package com.mememan.mememanmod.datagen;

import com.mememan.mememanmod.ManMemeMod;
import com.mememan.mememanmod.common.registry.MMMBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class MMMBlockModelProvider extends BlockModelProvider {
    public MMMBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ManMemeMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return ManMemeMod.MOD_NAME.concat(": Block Models");
    }

    @Override
    protected void registerModels() { //TODO Automate
        cubeAll(MMMBlocks.MANMEMEBLOCK.get());
    }

    protected void cubeAll(Block targetBlock) {
        cubeAll(ForgeRegistries.BLOCKS.getKey(targetBlock).getPath(), ManMemeMod.prefix("block/" + ForgeRegistries.BLOCKS.getKey(targetBlock).getPath()));
    }


    protected void cubeTop(Block targetBlock) {
        cubeTop(ForgeRegistries.BLOCKS.getKey(targetBlock).getPath(), ManMemeMod.prefix("block/" + ForgeRegistries.BLOCKS.getKey(targetBlock).getPath() + "_side"), ManMemeMod.prefix("block/" + ForgeRegistries.BLOCKS.getKey(targetBlock).getPath() + "_top"));
    }
}
