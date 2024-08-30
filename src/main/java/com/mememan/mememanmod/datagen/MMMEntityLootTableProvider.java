package com.mememan.mememanmod.datagen;


import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

public class MMMEntityLootTableProvider extends EntityLootSubProvider {
    protected MMMEntityLootTableProvider() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
    }
}
