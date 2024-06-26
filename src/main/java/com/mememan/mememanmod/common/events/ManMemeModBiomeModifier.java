package com.mememan.mememanmod.common.events;

import com.mememan.mememanmod.ManMemeMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class ManMemeModBiomeModifier implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> holder, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if(phase == Phase.ADD) {
        }

    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return null;
    }
}
//i dont get it