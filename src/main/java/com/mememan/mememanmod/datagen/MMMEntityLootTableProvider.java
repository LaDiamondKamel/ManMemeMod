package com.mememan.mememanmod.datagen;


import com.mememan.mememanmod.ManMemeMod;
import com.mememan.mememanmod.common.entity.creature.ManMeme;
import com.mememan.mememanmod.common.registry.MMMEntityTypes;
import com.mememan.mememanmod.common.registry.MMMItems;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Stream;

public class MMMEntityLootTableProvider extends EntityLootSubProvider {
    protected MMMEntityLootTableProvider() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(MMMEntityTypes.MANMEMEBOSS.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(MMMItems.MAN_MEME_MALLET.get()))
                .when(LootItemKilledByPlayerCondition.killedByPlayer()))); //Garantyyd MAN MEME MALLET DROP

        this.add(MMMEntityTypes.MANMEME.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(MMMItems.MAN_MEME_COIN_ITEM.get()))));

        this.add(MMMEntityTypes.MANMEMECOIN.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(MMMItems.MAN_MEME_COIN_ITEM.get()))));

        this.add(MMMEntityTypes.MEMEMANCAT.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(MMMItems.MANMEME_CHESTPLATE.get()))
                .when(LootItemKilledByPlayerCondition.killedByPlayer())));

        this.add(MMMEntityTypes.MEMEMAN.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Items.PORKCHOP))
                .when(LootItemKilledByPlayerCondition.killedByPlayer())));

        this.add(MMMEntityTypes.MANMEMEBOSS.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(MMMItems.MAN_MEME_MALLET.get()))
                .when(LootItemKilledByPlayerCondition.killedByPlayer())));

        this.add(MMMEntityTypes.RATROD.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(0.5F))
                .add(LootItem.lootTableItem(Items.RABBIT))
                .when(LootItemKilledByPlayerCondition.killedByPlayer())));

        this.add(MMMEntityTypes.MANMEMEVILLAGER.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(MMMItems.MAN_MEME_COIN_ITEM.get()))
                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return MMMLootTableProvider.knownSet(ForgeRegistries.ENTITY_TYPES).stream();
    }
}
