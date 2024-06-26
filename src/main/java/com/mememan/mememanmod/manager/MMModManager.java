package com.mememan.mememanmod.manager;


import net.minecraftforge.eventbus.api.IEventBus;

public final class MMModManager {

    public static void registerAll(IEventBus modBus, IEventBus forgeBus) {
        MMMEventManager.registerEvents(modBus, forgeBus);
        MMMRegistryManager.registerRegistries(modBus);
    }
}

