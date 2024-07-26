package com.mememan.mememanmod.common.entity.boss;

import com.mememan.mememanmod.common.entity.goals.ManMemeBossAttackGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ManMemeBoss extends Monster implements GeoEntity {
    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.man_meme_boss.idle");

    protected static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.man_meme_boss.walk");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public ManMemeBoss(EntityType<com.mememan.mememanmod.common.entity.boss.ManMemeBoss> entityType, net.minecraft.world.level.Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.03D).add(Attributes.ARMOR, 20.0D).add(Attributes.MAX_HEALTH, 1000.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 3f));

        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(1, new ManMemeBossAttackGoal(this, 4.0, false));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));

    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "animation.man_meme_boss.walk", 3, this::walkAnimController));
    }
    protected <C extends com.mememan.mememanmod.common.entity.boss.ManMemeBoss> PlayState walkAnimController(final AnimationState<C> event) {
        if (event.isMoving())
            return event.setAndContinue(WALK);

        else if (!event.isMoving())
            return event.setAndContinue(IDLE);

        else
            return PlayState.STOP;
    }

    @Override
    public double getBoneResetTime() {
        return GeoEntity.super.getBoneResetTime();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}
