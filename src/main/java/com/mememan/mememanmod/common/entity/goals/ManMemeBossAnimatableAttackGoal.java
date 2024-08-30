package com.mememan.mememanmod.common.entity.goals;


import com.mememan.mememanmod.common.entity.boss.ManMemeBoss;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;

public class ManMemeBossAnimatableAttackGoal extends Goal {

    private final ManMemeBoss manMemeBoss;

    int attackAnimationTimeout = 14;

    int attackCooldown = 40;

    int animationLength = 30;

    boolean attackState = false;

    public ManMemeBossAnimatableAttackGoal(ManMemeBoss manMemeBoss) {
        this.manMemeBoss = manMemeBoss;
    }


    @Override
    public void tick() {
        super.tick();

        if (attackAnimationTimeout > 0) {
            attackAnimationTimeout--;
        }

        if (attackAnimationTimeout <= 0 && !attackState) {
            List<LivingEntity> entitiesInMeleeRange = manMemeBoss.level().getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, manMemeBoss, new AABB(manMemeBoss.getX() - 5, manMemeBoss.getY() - 5, manMemeBoss.getZ() - 5, manMemeBoss.getX() + 5, manMemeBoss.getY() + 5, manMemeBoss.getZ() + 5));


            for (Entity entity : entitiesInMeleeRange) {

                if (entity.isAlive() && !entity.noPhysics) {
                    manMemeBoss.doHurtTarget(entity);
                }
            }
            attackState = true;
        }
    }


    @Override
    public void start() {
        super.start();
        attackCooldown = 40;
        attackAnimationTimeout = 14;
        animationLength = 30;
        manMemeBoss.setAttacking(true);
    }


    @Override
    public void stop() {
        super.stop();
        attackAnimationTimeout = 0;
        manMemeBoss.setAttacking(false);
        attackState = false;
    }

    @Override
    public boolean canContinueToUse() {
        return !manMemeBoss.isDeadOrDying() && manMemeBoss.getTarget() != null && !manMemeBoss.getTarget().isDeadOrDying() && --animationLength >= 0;
    }


    @Override
    public boolean canUse() {
        return manMemeBoss.isAlive() && manMemeBoss.getTarget() != null && manMemeBoss.getTarget().isAlive() && manMemeBoss.distanceTo(manMemeBoss.getTarget()) < 4;
        //if boss is alive, if target isnt null, if target is alive
    }
}