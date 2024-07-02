package com.mememan.mememanmod.client.events.renderer;

import com.mememan.mememanmod.client.events.model.ManMemeModel;
import com.mememan.mememanmod.client.events.model.MemeManModel;
import com.mememan.mememanmod.common.entity.ManMeme;
import com.mememan.mememanmod.common.entity.MemeMan;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MemeManRenderer extends GeoEntityRenderer<MemeMan> {


    public MemeManRenderer(EntityRendererProvider.Context context) {
        super(context, new MemeManModel());
    }
}
