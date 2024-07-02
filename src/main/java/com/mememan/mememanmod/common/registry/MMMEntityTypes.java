package com.mememan.mememanmod.common.registry;

import com.mememan.mememanmod.ManMemeMod;
import com.mememan.mememanmod.common.entity.ManMeme;
import com.mememan.mememanmod.common.entity.ManMemeCoin;
import com.mememan.mememanmod.common.entity.MemeMan;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MMMEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ManMemeMod.MODID);
    public static final RegistryObject<EntityType<ManMeme>> MANMEME = ENTITY_TYPES.register("manmeme", () -> EntityType.Builder.of(ManMeme::new, MobCategory.CREATURE).sized(1.0F, 1.0F).build(new ResourceLocation(ManMemeMod.MODID, "manmeme").toString()));
    public static final RegistryObject<EntityType<ManMemeCoin>> MANMEMECOIN = ENTITY_TYPES.register("manmemecoin", () -> EntityType.Builder.of(ManMemeCoin::new, MobCategory.CREATURE).sized(1.0F, 1.0F).build(new ResourceLocation(ManMemeMod.MODID, "manmemecoin").toString()));

    public static final RegistryObject<EntityType<MemeMan>> MEMEMAN = ENTITY_TYPES.register("mememan", () -> EntityType.Builder.of((EntityType.EntityFactory<MemeMan>) MemeMan::new, MobCategory.CREATURE).sized(1.0F, 1.0F).build(new ResourceLocation(ManMemeMod.MODID, "mememan").toString()));
}



