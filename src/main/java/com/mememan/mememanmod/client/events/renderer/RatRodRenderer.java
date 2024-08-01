package com.mememan.mememanmod.client.events.renderer;

import com.mememan.mememanmod.client.events.model.ManMemeBossModel;
import com.mememan.mememanmod.client.events.model.RatRodModel;
import com.mememan.mememanmod.common.entity.creature.RatRod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RatRodRenderer extends GeoEntityRenderer<RatRod> {

    public RatRodRenderer(EntityRendererProvider.Context context) {
        super(context, new RatRodModel());
    }
}
