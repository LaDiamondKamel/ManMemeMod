package com.mememan.mememanmod.client.events.renderer;


import com.mememan.mememanmod.client.events.model.MemeManCatModel;
import com.mememan.mememanmod.common.entity.hostile.MemeManCat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MemeManCatRenderer extends GeoEntityRenderer<MemeManCat> {

    public MemeManCatRenderer(EntityRendererProvider.Context context) { super(context, new MemeManCatModel()); }
}

