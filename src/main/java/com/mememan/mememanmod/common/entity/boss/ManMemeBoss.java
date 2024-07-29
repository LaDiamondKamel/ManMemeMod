package com.mememan.mememanmod.common.entity.boss;

import com.mememan.mememanmod.common.entity.goals.ManMemeBossAnimatableAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
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

    protected static final RawAnimation SWING = RawAnimation.begin().thenPlayAndHold("animation.man_meme_boss.swing");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(ManMemeBoss.class, EntityDataSerializers.BOOLEAN);

    public ManMemeBoss(EntityType<com.mememan.mememanmod.common.entity.boss.ManMemeBoss> entityType, net.minecraft.world.level.Level level) {
        super(entityType, level);
    }

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(ManMemeBoss.class, EntityDataSerializers.BOOLEAN);

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.20D).add(Attributes.ARMOR, 20.0D).add(Attributes.MAX_HEALTH, 1000.0D).add(Attributes.ATTACK_KNOCKBACK, 2.0f).add(Attributes.ATTACK_DAMAGE, 20f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(1, new ManMemeBossAnimatableAttackGoal(this, 1.0D, true));


        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
    //    this.goalSelector.addGoal(1, new ManMemeBossAttackGoal(this, 4.0, false));

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


    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
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
