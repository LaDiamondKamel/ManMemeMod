package com.mememan.mememanmod.common.registry;

import com.mememan.mememanmod.ManMemeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.levelgen.structure.Structure;

public class MMMTags {
    public static class EntityTypeTags {

        private static TagKey<EntityType<?>> createEntityTag(String tagName) {
            return TagKey.create(Registries.ENTITY_TYPE, ManMemeMod.prefix(tagName));
        }
    }

    public static class BlockTags {

        private static TagKey<Block> createBlockTag(String tagName) {
            return TagKey.create(Registries.BLOCK, ManMemeMod.prefix(tagName));
        }
    }

    public static class EffectTags {

        private static TagKey<MobEffect> createMobEffectTag(String tagName) {
            return TagKey.create(Registries.MOB_EFFECT, ManMemeMod.prefix(tagName));
        }
    }

    public static class ItemTags {

        private static TagKey<Item> createItemTag(String tagName) {
            return TagKey.create(Registries.ITEM, ManMemeMod.prefix(tagName));
        }
    }

    public static class BiomeTags {

        private static TagKey<Biome> createBiomeTag(String tagName) {
            return TagKey.create(Registries.BIOME, ManMemeMod.prefix(tagName));
        }
    }

    public static class StructureTags {

        private static TagKey<Structure> createBiomeTag(String tagName) {
            return TagKey.create(Registries.STRUCTURE, ManMemeMod.prefix(tagName));
        }
    }

    public static class BannerPatternTags {

        private static TagKey<BannerPattern> tag(String tagName) {
            return TagKey.create(Registries.BANNER_PATTERN, ManMemeMod.prefix(tagName));
        }
    }

    public static void initialize() {}
}