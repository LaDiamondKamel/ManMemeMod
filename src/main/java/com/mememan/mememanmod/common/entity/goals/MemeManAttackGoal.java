package com.mememan.mememanmod.common.entity.goals;


import com.mememan.mememanmod.common.entity.hostile.MemeMan;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class MemeManAttackGoal extends MeleeAttackGoal {
    private final MemeMan memeMan;

    public MemeManAttackGoal(MemeMan p_26019_, double p_26020_, boolean p_26021_) {
        super(p_26019_, p_26020_, p_26021_);
        this.memeMan = p_26019_;
    }


    public void stop() {
        super.stop();
        this.memeMan.setAggressive(false);
    }
}
