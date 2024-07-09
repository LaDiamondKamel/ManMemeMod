package com.mememan.mememanmod.common.entity.goals;


import com.mememan.mememanmod.common.entity.MemeManCat;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class MemeManCatAttackGoal extends MeleeAttackGoal {
    private final MemeManCat memeManCat;

    public MemeManCatAttackGoal(MemeManCat p_26019_, double p_26020_, boolean p_26021_) {
        super(p_26019_, p_26020_, p_26021_);
        this.memeManCat = p_26019_;
    }


    public void stop() {
        super.stop();
        this.memeManCat.setAggressive(false);
    }
}