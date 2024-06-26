package com.mememan.mememanmod.manager;

import com.mememan.mememanmod.common.registry.*;
import net.minecraftforge.eventbus.api.IEventBus;

public final class MMMRegistryManager {

    static void registerRegistries(IEventBus modBus) {
        MMMTags.initialize();

        MMMCreativeModeTabs.CREATIVE_MODE_TABS.register(modBus);
        MMMBlocks.ITEM_BLOCKS.register(modBus);
        MMMBlocks.BLOCKS.register(modBus);
        MMMItems.ITEMS.register(modBus);
        MMMEntityTypes.ENTITY_TYPES.register(modBus);
    }
}
