package com.mememan.mememanmod.common.events;

import com.mememan.mememanmod.common.entity.boss.ManMemeBoss;
import com.mememan.mememanmod.common.entity.creature.ManMeme;
import com.mememan.mememanmod.common.entity.creature.ManMemeCoin;
import com.mememan.mememanmod.common.entity.creature.ManMemeVillager;
import com.mememan.mememanmod.common.entity.creature.RatRod;
import com.mememan.mememanmod.common.entity.hostile.MemeMan;
import com.mememan.mememanmod.common.entity.hostile.MemeManCat;
import com.mememan.mememanmod.common.registry.MMMEntityTypes;
import com.mememan.mememanmod.datagen.*;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class MMMCommonSetupEvents {
    public static class ModEvents {
        @SubscribeEvent
        public static void onFMLCommonSetupEvent(FMLCommonSetupEvent event) {

        }

        @SubscribeEvent
        public static void onAttributeRegisterer(EntityAttributeCreationEvent event) {
            event.put(MMMEntityTypes.MANMEME.get(), ManMeme.createAttributes().build());
            event.put(MMMEntityTypes.MANMEMECOIN.get(), ManMemeCoin.createAttributes().build());
            event.put(MMMEntityTypes.MEMEMAN.get(), MemeMan.createAttributes().build());
            event.put(MMMEntityTypes.MEMEMANCAT.get(), MemeManCat.createAttributes().build());
            event.put(MMMEntityTypes.MANMEMEVILLAGER.get(), ManMemeVillager.createAttributes().build());
            event.put(MMMEntityTypes.MANMEMEBOSS.get(), ManMemeBoss.createAttributes().build());
            event.put(MMMEntityTypes.RATROD.get(), RatRod.createAttributes().build());
        }



        @SubscribeEvent
        public static void onGatherDataEvent(GatherDataEvent event) {
                DataGenerator dataGen = event.getGenerator();
                PackOutput dataGenPackOutput = dataGen.getPackOutput();
                final ExistingFileHelper curFileHelper = event.getExistingFileHelper();
                final CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

                dataGen.addProvider(event.includeClient(), new MMMBlockModelProvider(dataGenPackOutput, curFileHelper));
                dataGen.addProvider(event.includeClient(), new MMMBlockStateProvider(dataGenPackOutput, curFileHelper));
                dataGen.addProvider(event.includeClient(), new MMMItemModelProvider(dataGenPackOutput, curFileHelper));
                dataGen.addProvider(event.includeClient(), new MMMLanguageProvider(dataGenPackOutput));
                dataGen.addProvider(event.includeClient(), new MMMWorldGenProvider(dataGenPackOutput, lookupProvider));

                MMMTagProvider.BlockTagProvider blockTagProvider = new MMMTagProvider.BlockTagProvider(dataGenPackOutput, lookupProvider, curFileHelper);

                dataGen.addProvider(event.includeServer(), new LootTableProvider(dataGenPackOutput, Collections.emptySet(), ObjectArrayList.of(new LootTableProvider.SubProviderEntry(MMMBlockLootTableProvider::new, LootContextParamSets.BLOCK))));
                dataGen.addProvider(event.includeServer(), blockTagProvider);
                dataGen.addProvider(event.includeServer(), new MMMTagProvider.ItemTagProvider(dataGenPackOutput, lookupProvider, blockTagProvider.contentsGetter(), curFileHelper));
                dataGen.addProvider(event.includeServer(), new MMMTagProvider.EntityTypeTagProvider(dataGenPackOutput, lookupProvider, curFileHelper));
                dataGen.addProvider(event.includeServer(), new MMMTagProvider.PaintingTagProvider(dataGenPackOutput, lookupProvider, curFileHelper));
                dataGen.addProvider(event.includeServer(), new MMMRecipeProvider(dataGenPackOutput));
        }
    }

    public static class ForgeEvents {

    }
}
