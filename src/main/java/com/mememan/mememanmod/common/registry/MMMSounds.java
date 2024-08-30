package com.mememan.mememanmod.common.registry;

import com.mememan.mememanmod.ManMemeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class MMMSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ManMemeMod.MODID);

    public static RegistryObject<SoundEvent> MAN_MEME_BOSS_THEME = registerSoundEvents("man_meme_boss_theme");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ManMemeMod.MODID, name)));
    }
}