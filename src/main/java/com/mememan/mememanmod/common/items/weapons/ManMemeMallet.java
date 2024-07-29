package com.mememan.mememanmod.common.items.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ManMemeMallet extends AxeItem {


    public ManMemeMallet(Tier p_40521_, float p_40522_, float p_40523_, Properties p_40524_) {
        super(p_40521_, p_40522_, p_40523_, p_40524_);
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (Screen.hasShiftDown() || Screen.hasControlDown())
            pTooltipComponents.add(Component.literal("Man Meme Mallet. The strongest weapon, only obtainable by killing the Man Meme Boss.").withStyle(ChatFormatting.GOLD));
        else
            pTooltipComponents.add(Component.literal("Hold down Shift or Control for more info").withStyle(ChatFormatting.GRAY));
    }

    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
        pTarget.knockback(1.0d, 1.0d, 1.0d);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    
}
