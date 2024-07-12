package com.mememan.mememanmod.worldgen.portal;

import com.mememan.mememanmod.common.custom.block.ManMemePortalBlock;
import com.mememan.mememanmod.common.registry.MMMBlocks;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class MMMPortal implements ITeleporter {
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;

    public MMMPortal(BlockPos pos, boolean insideDim) {
        thisPos = pos;
        insideDimension = insideDim;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        repositionEntity.apply(false);

        BlockPos curPos = entity.blockPosition();
        entity.moveTo(curPos.getX() + 0.5, destWorld.getHeight(Heightmap.Types.WORLD_SURFACE, curPos.getX(), curPos.getZ()), curPos.getZ() + 0.5);

        return entity;
    }

    @Override
    public boolean isVanilla() {
        return false;
    }

    public Sound getSound() {
        return null;
    }


}