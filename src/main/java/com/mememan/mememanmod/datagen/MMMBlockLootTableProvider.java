package com.mememan.mememanmod.datagen;

import com.mememan.mememanmod.common.registry.MMMBlocks;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.stream.Collectors;

public class MMMBlockLootTableProvider extends BlockLootSubProvider {

    public MMMBlockLootTableProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() { //TODO Automate
        dropSelf(MMMBlocks.MANMEMEBLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MMMBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .collect(Collectors.toCollection(ObjectArrayList::new));
    }
}
