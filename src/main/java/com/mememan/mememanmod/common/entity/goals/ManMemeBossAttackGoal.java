package com.mememan.mememanmod.common.entity.goals;


import com.mememan.mememanmod.common.entity.boss.ManMemeBoss;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class ManMemeBossAttackGoal extends MeleeAttackGoal {
    private final ManMemeBoss manMemeBoss;

    public ManMemeBossAttackGoal(ManMemeBoss p_26019_, double p_26020_, boolean p_26021_) {
        super(p_26019_, p_26020_, p_26021_);
        this.manMemeBoss = p_26019_;
    }


    public void stop() {
        super.stop();
        this.manMemeBoss.setAggressive(false);
    }
}