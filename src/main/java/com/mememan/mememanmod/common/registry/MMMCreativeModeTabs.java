package com.mememan.mememanmod.common.registry;

import com.mememan.mememanmod.ManMemeMod;
import com.mememan.mememanmod.common.registry.MMMBlocks;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Collectors;

public class MMMCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ManMemeMod.MODID);

    public static final RegistryObject<CreativeModeTab> MANMEMEMOD_ITEMS = CREATIVE_MODE_TABS.register("manmeme_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativemodetab.mmm.manmememod_items"))
            .displayItems((displayParams, curOutput) -> curOutput.acceptAll(MMMItems.ITEMS.getEntries().stream().map(RegistryObject::get).filter(itemEntry -> !(itemEntry instanceof TieredItem) && !(itemEntry instanceof ArmorItem)).map(Item::getDefaultInstance).collect(Collectors.toCollection(ObjectArrayList::new)), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY))
            .icon(() -> MMMItems.ITEMS.getEntries().stream().map(RegistryObject::get).map(Item::asItem).map(Item::getDefaultInstance).findFirst().get())
            .build());

    public static final RegistryObject<CreativeModeTab> MANMEMEMOD_GEAR = CREATIVE_MODE_TABS.register("manmeme_gear", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativemodetab.mmm.manmememod_gear"))
            .displayItems((displayParams, curOutput) -> curOutput.acceptAll(MMMItems.ITEMS.getEntries().stream().map(RegistryObject::get).filter(itemEntry -> itemEntry instanceof TieredItem || itemEntry instanceof ArmorItem).map(Item::getDefaultInstance).collect(Collectors.toCollection(ObjectArrayList::new)), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY))
            .icon(() -> MMMItems.ITEMS.getEntries().stream().map(RegistryObject::get).map(Item::asItem).map(Item::getDefaultInstance).findFirst().get())
            .build());

    public static final RegistryObject<CreativeModeTab> MANMEMEMOD_BLOCKS = CREATIVE_MODE_TABS.register("manmeme_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativemodetab.mmm.manmememod_blocks"))
            .displayItems((displayParams, curOutput) -> curOutput.acceptAll(MMMBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).map(Block::asItem).map(Item::getDefaultInstance).collect(Collectors.toCollection(ObjectArrayList::new)), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY))
            .icon(() -> MMMItems.ITEMS.getEntries().stream().map(RegistryObject::get).map(Item::asItem).map(Item::getDefaultInstance).findFirst().get())
            .build());
}
