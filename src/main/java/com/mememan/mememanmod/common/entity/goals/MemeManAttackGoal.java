package com.mememan.mememanmod.common.entity.goals;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import com.mememan.mememanmod.common.entity.MemeMan;
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
