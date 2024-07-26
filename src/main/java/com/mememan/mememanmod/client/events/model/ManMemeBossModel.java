package com.mememan.mememanmod.client.events.model;

import com.mememan.mememanmod.ManMemeMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class ManMemeBossModel extends GeoModel {
    @Override
    public ResourceLocation getModelResource(GeoAnimatable geoAnimatable) {
        return new ResourceLocation(ManMemeMod.MODID, "geo/man_meme_boss.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeoAnimatable geoAnimatable) {
        return new ResourceLocation(ManMemeMod.MODID, "textures/entity/man_meme_boss_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeoAnimatable geoAnimatable) {
        return new ResourceLocation(ManMemeMod.MODID, "animations/man_meme_boss.animation.json");
    }
}