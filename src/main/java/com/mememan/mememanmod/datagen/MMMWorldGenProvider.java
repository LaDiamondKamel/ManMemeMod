package com.mememan.mememanmod.datagen;

import com.mememan.mememanmod.worldgen.biome.MMMBiomes;
import com.mememan.mememanmod.worldgen.dimension.MMMDimensions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MMMWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, MMMDimensions::bootstrapType)
            .add(Registries.BIOME, MMMBiomes::bootstrap)
            .add(Registries.LEVEL_STEM, MMMDimensions::bootstrapStem);
    public MMMWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, Set<String> modIds) {
        super(output, registries, modIds);
    }
}
//manmeme is so manmeme (still) frfr