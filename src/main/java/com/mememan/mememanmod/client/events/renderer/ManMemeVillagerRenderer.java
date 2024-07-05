package com.mememan.mememanmod.client.events.renderer;

import com.mememan.mememanmod.client.events.model.ManMemeVillagerModel;
import com.mememan.mememanmod.common.entity.ManMemeVillager;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ManMemeVillagerRenderer extends GeoEntityRenderer<ManMemeVillager> {

    public ManMemeVillagerRenderer(EntityRendererProvider.Context context) { super(context, new ManMemeVillagerModel()); }
}

