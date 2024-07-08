package com.mememan.mememanmod.common.registry;
import com.mememan.mememanmod.ManMemeMod;
import com.mememan.mememanmod.common.items.weapons.BeardedSword;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class MMMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ManMemeMod.MODID);

    public static final RegistryObject<Item> MAN_MEME_COIN_ITEM = ITEMS.register("man_meme_coin_item", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BEARDED_SWORD = ITEMS.register("bearded_sword", () -> new BeardedSword(Tiers.NETHERITE, 6, -2.4F, new Item.Properties()));

}
//manmeme is so manmeme frfr
