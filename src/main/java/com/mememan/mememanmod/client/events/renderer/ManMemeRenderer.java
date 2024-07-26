package com.mememan.mememanmod.client.events.renderer;

import com.mememan.mememanmod.client.events.model.ManMemeModel;
import com.mememan.mememanmod.common.entity.creature.ManMeme;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ManMemeRenderer extends GeoEntityRenderer<ManMeme> {


    public ManMemeRenderer(EntityRendererProvider.Context context) { super(context, new ManMemeModel()); }
}
