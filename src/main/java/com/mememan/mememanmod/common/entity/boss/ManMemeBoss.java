package com.mememan.mememanmod.common.entity.boss;

import com.mememan.mememanmod.common.entity.goals.ManMemeBossAnimatableAttackGoal;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import org.checkerframework.checker.units.qual.C;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ManMemeBoss extends Monster implements GeoEntity {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(ManMemeBoss.class, EntityDataSerializers.BOOLEAN);


    public final AnimationState attackAnimationState = new AnimationState(this, 2f, 2f, 2f,true);
    public int attackAnimationTimeout = 0;

    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.man_meme_boss.idle");

    protected static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.man_meme_boss.walk");

    protected static final RawAnimation DEATH = RawAnimation.begin().thenPlayAndHold("animation.man_meme_boss.death");

    protected static final RawAnimation SWING = RawAnimation.begin().thenPlayAndHold("animation.man_meme_boss.swing");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(ManMemeBoss.class, EntityDataSerializers.BOOLEAN);

    private ServerLevel level;

    private final ServerBossEvent bossEvent =
            new ServerBossEvent(Component.literal("Man Meme Boss"), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_20);


    public ManMemeBoss(EntityType<com.mememan.mememanmod.common.entity.boss.ManMemeBoss> entityType, net.minecraft.world.level.Level level) {
        super(entityType, level);
    }
    //im a retard, arent i?

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.20D).add(Attributes.ARMOR, 20.0D).add(Attributes.MAX_HEALTH, 1000.0D).add(Attributes.ATTACK_KNOCKBACK, 5.0f).add(Attributes.ATTACK_DAMAGE, 70.0f);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MoveTowardsTargetGoal(this, 1.0D, 1.0F));
        this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new ManMemeBossAnimatableAttackGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.addBehaviourGoals();
    }


    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(1, new ManMemeBossAnimatableAttackGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
    }


    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "animation.man_meme_boss", 3, this::walkAnimController).triggerableAnim("death", DEATH));
        controllerRegistrar.add(new AnimationController<>(this, "animation.man_meme_boss.swing", 3, this::swingAnimController));
    }


    protected <C extends com.mememan.mememanmod.common.entity.boss.ManMemeBoss> PlayState walkAnimController(final AnimationState<C> event) {
        if (event.isMoving())
            return event.setAndContinue(WALK);

        else if (!event.isMoving())
            return event.setAndContinue(IDLE);

        else
            return PlayState.CONTINUE;
    }

    protected <D extends com.mememan.mememanmod.common.entity.boss.ManMemeBoss> PlayState swingAnimController(final AnimationState<D> event) {
        if (isAttacking()) {
            return event.setAndContinue(SWING);
        } else event.getController().forceAnimationReset();
        return !isAttacking() || isDeadOrDying() ? PlayState.STOP : PlayState.CONTINUE;
    }


    @Override
    public void tickDeath() {
        if (!level().isClientSide) {

            triggerAnim("animation.man_meme_boss", "death");

            this.deathTime++;

            if (this.deathTime > 100)

                this.remove(RemovalReason.KILLED);


            if (level instanceof ServerLevel) {

                this.level.broadcastEntityEvent(this, (byte) 60);

                this.die(this.getLastDamageSource() != null ?
                        this.getLastDamageSource() : new DamageSource((Holder<DamageType>) DamageTypes.GENERIC));
            }
        }
    }


    @Override
    public void die(DamageSource damageSource) {
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


    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }


    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }


    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}