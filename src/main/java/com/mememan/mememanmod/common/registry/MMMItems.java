package com.mememan.mememanmod.common.registry;
import com.mememan.mememanmod.ManMemeMod;
import com.mememan.mememanmod.common.items.weapons.BeardedSword;
import com.mememan.mememanmod.common.items.weapons.ManMemeMallet;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ManMemeMod.MODID);

    public static final RegistryObject<Item> MAN_MEME_COIN_ITEM = ITEMS.register("man_meme_coin_item", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BEARDED_SWORD = ITEMS.register("bearded_sword", () -> new BeardedSword(Tiers.NETHERITE, 6, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> MAN_MEME_MALLET = ITEMS.register("man_meme_mallet", () -> new ManMemeMallet(Tiers.WOOD, 70, 2.4f, new Item.Properties().durability(10000).craftRemainder(Items.GOLD_INGOT)));

    public static final RegistryObject<Item> MANMEME_HELMET = ITEMS.register("manmeme_helmet", () -> new ArmorItem(MMMArmorMaterial.MANMEME, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MANMEME_CHESTPLATE = ITEMS.register("manmeme_chestplate", () -> new ArmorItem(MMMArmorMaterial.MANMEME, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> MANMEME_LEGGINGS = ITEMS.register("manmeme_leggings", () -> new ArmorItem(MMMArmorMaterial.MANMEME, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> MANMEME_BOOTS = ITEMS.register("manmeme_boots", () -> new ArmorItem(MMMArmorMaterial.MANMEME, ArmorItem.Type.BOOTS, new Item.Properties()));

}
//manmeme is so manmeme frfr
