package com.mememan.mememanmod.client.events.renderer;

import com.mememan.mememanmod.client.events.model.ManMemeBossModel;
import com.mememan.mememanmod.common.entity.boss.ManMemeBoss;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ManMemeBossRenderer extends GeoEntityRenderer<ManMemeBoss> {


    public ManMemeBossRenderer(EntityRendererProvider.Context context) {
        super(context, new ManMemeBossModel());
    }
}

