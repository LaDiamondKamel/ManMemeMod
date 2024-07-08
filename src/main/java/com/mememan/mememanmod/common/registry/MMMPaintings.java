package com.mememan.mememanmod.common.registry;

import com.mememan.mememanmod.ManMemeMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMMPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, ManMemeMod.MODID);

    public static final RegistryObject<PaintingVariant> CHADVSVIRGIN = PAINTING_VARIANTS.register("chadvsvirgin",
            () -> new PaintingVariant(128, 100));

    public static final RegistryObject<PaintingVariant> CHADWATER = PAINTING_VARIANTS.register("chadwater",
            () -> new PaintingVariant(32, 32));

    public static final RegistryObject<PaintingVariant> MANMEME = PAINTING_VARIANTS.register("manmeme",
            () -> new PaintingVariant(16, 16));

    public static final RegistryObject<PaintingVariant> FNAFMANMEME = PAINTING_VARIANTS.register("fnafmanmeme",
            () -> new PaintingVariant(32, 16));

    public static void initialize(IEventBus modBus) {
        PAINTING_VARIANTS.register(modBus);
    }


}
