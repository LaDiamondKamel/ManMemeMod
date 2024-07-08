package com.mememan.mememanmod;

import com.mememan.mememanmod.manager.MMModManager;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Locale;


@Mod(ManMemeMod.MODID)
public class ManMemeMod {
    public static final String MODID = "manmememod";
    public static final String MOD_NAME = "Man Meme Mod";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static ManMemeMod INSTANCE;
    public ManMemeMod() {
        INSTANCE = this;

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;


        if (modBus != null && forgeBus != null) MMModManager.registerAll(modBus, forgeBus);
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MODID, path.toLowerCase(Locale.ROOT));
    }

    @Nullable
    public static ManMemeMod getInstance() {
        return INSTANCE;
    }

    public static boolean isLoaded() {
        return INSTANCE != null;
    }
}
