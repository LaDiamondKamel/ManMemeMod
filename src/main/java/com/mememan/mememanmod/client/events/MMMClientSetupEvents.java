package com.mememan.mememanmod.client.events;

import com.mememan.mememanmod.client.events.renderer.*;
import com.mememan.mememanmod.common.registry.MMMEntityTypes;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class MMMClientSetupEvents {
    public static void onFMLClientSetupEvent(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void onRegisterRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MMMEntityTypes.MANMEME.get(), ManMemeRenderer::new);
        event.registerEntityRenderer(MMMEntityTypes.MANMEMECOIN.get(), ManMemeCoinRenderer::new);
        event.registerEntityRenderer(MMMEntityTypes.MEMEMAN.get(), MemeManRenderer::new);
        event.registerEntityRenderer(MMMEntityTypes.MEMEMANCAT.get(), MemeManCatRenderer::new);
        event.registerEntityRenderer(MMMEntityTypes.MANMEMEVILLAGER.get(), ManMemeVillagerRenderer::new);
        event.registerEntityRenderer(MMMEntityTypes.MANMEMEBOSS.get(), ManMemeBossRenderer::new);
    }
}
