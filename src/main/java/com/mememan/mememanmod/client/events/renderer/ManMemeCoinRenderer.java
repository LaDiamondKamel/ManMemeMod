package com.mememan.mememanmod.client.events.renderer;

import com.mememan.mememanmod.client.events.model.ManMemeCoinModel;
import com.mememan.mememanmod.client.events.model.ManMemeModel;
import com.mememan.mememanmod.common.entity.ManMeme;
import com.mememan.mememanmod.common.entity.ManMemeCoin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ManMemeCoinRenderer extends GeoEntityRenderer<ManMemeCoin> {


    public ManMemeCoinRenderer(EntityRendererProvider.Context context) {
        super(context, new ManMemeCoinModel());
    }
}
