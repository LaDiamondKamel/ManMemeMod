package com.mememan.mememanmod.datagen;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class MMMLootTableProvider extends LootTableProvider {
    public MMMLootTableProvider(PackOutput p_254123_) {
        super(p_254123_, Set.of(), ObjectArrayList.of(
                new SubProviderEntry(MMMEntityLootTableProvider::new, LootContextParamSets.ENTITY)
        ));
    }
}
