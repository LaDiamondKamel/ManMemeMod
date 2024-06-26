package com.mememan.mememanmod.manager;

import com.mememan.mememanmod.client.events.MMMClientMiscEvents;
import com.mememan.mememanmod.client.events.MMMClientSetupEvents;
import com.mememan.mememanmod.common.events.MMMCommonMiscEvents;
import com.mememan.mememanmod.common.events.MMMCommonSetupEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLEnvironment;

public final class MMMEventManager {

    static void registerEvents(IEventBus modBus, IEventBus forgeBus) {
        registerCommonEvents(modBus, forgeBus);
        registerClientEvents(modBus, forgeBus);
        registerServerEvents(modBus, forgeBus);
    }

    private static void registerClientEvents(IEventBus modBus, IEventBus forgeBus) {
        if (FMLEnvironment.dist.isClient()) {
            modBus.register(MMMClientSetupEvents.class);

            forgeBus.register(MMMClientMiscEvents.class);
        }
    }

    private static void registerCommonEvents(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(MMMNetworkManager::registerPackets);
        modBus.register(MMMCommonSetupEvents.ModEvents.class);

        forgeBus.register(MMMCommonMiscEvents.class);
        forgeBus.register(MMMCommonSetupEvents.ForgeEvents.class);
    }

    private static void registerServerEvents(IEventBus modBus, IEventBus forgeBus) {
        if (FMLEnvironment.dist.isDedicatedServer()) {

        }
    }
}
