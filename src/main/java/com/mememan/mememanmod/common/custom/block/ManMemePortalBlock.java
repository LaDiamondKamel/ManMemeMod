package com.mememan.mememanmod.common.custom.block;

import com.mememan.mememanmod.worldgen.dimension.MMMDimensions;
import com.mememan.mememanmod.worldgen.portal.MMMPortal;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ManMemePortalBlock extends Block {

    public ManMemePortalBlock(Properties p_49795_) {
        super(p_49795_);
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.canChangeDimensions()) {
            handleManMemePortal(pPlayer, pPos);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }
    }

    private void handleManMemePortal(Entity player, BlockPos pPos) {
        if (player.level() instanceof ServerLevel serverlevel) {
            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey = player.level().dimension() == MMMDimensions.MANMEMEDIM_LEVEL_KEY ?
                    Level.OVERWORLD : MMMDimensions.MANMEMEDIM_LEVEL_KEY;

            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            if (portalDimension != null && !player.isPassenger()) {
                if(resourcekey == MMMDimensions.MANMEMEDIM_LEVEL_KEY) {
                    player.changeDimension(portalDimension, new MMMPortal(pPos, true));
                } else {
                    player.changeDimension(portalDimension, new MMMPortal(pPos, false));
                }
            }
        }
    }
}
