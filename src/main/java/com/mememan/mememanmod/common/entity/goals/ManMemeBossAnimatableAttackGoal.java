package com.mememan.mememanmod.common.entity.goals;


import com.mememan.mememanmod.common.entity.boss.ManMemeBoss;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ManMemeBossAnimatableAttackGoal extends Goal {

    private final ManMemeBoss manMemeBoss;

    int attackAnimationTimeout = 60;

    int attackCooldown = 40;

    public ManMemeBossAnimatableAttackGoal(ManMemeBoss manMemeBoss) {
        this.manMemeBoss = manMemeBoss;
    }


    @Override
    public void tick() {
        super.tick();

        if (attackAnimationTimeout > 0) {
            List<ManMemeBoss> entitiesInMeleeRange = manMemeBoss.level().getNearbyEntities(ManMemeBoss.class, TargetingConditions.DEFAULT, manMemeBoss, new AABB(manMemeBoss.getX() - 5, manMemeBoss.getY() - 5, manMemeBoss.getZ() - 5, manMemeBoss.getX() + 5, manMemeBoss.getY() + 5, manMemeBoss.getZ() + 5));


            attackAnimationTimeout--;
        }

        if (attackAnimationTimeout == 0) {

        }
    }


    @Override
    public void start() {
        super.start();
        manMemeBoss.triggerAnim("animation.man_meme_boss", "swing");
        attackCooldown = 40;
    }


    @Override
    public void stop() {
        super.stop();
        attackAnimationTimeout = 0;
    }


    @Override
    public boolean canUse() {
        return manMemeBoss.isAlive() && manMemeBoss.getTarget() != null && manMemeBoss.getTarget().isAlive() && manMemeBoss.distanceTo(manMemeBoss.getTarget()) < 4;
        //if boss is alive, if target isnt null, if target is alive
    }
}