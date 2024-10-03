package com.mememan.mememanmod.common.items.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ManPickAxe extends PickaxeItem {
    public ManPickAxe(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42961_, p_42962_, p_42963_, p_42964_);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (Screen.hasShiftDown() || Screen.hasControlDown()) pTooltipComponents.add(Component.literal("ManPickAxe").withStyle(ChatFormatting.YELLOW));
        else pTooltipComponents.add(Component.literal("MAN OH MAN THE MEME'S ON FIRE").withStyle(ChatFormatting.OBFUSCATED));
    }

    @Override
    public boolean isFireResistant() {
        return super.isFireResistant();
    }
}
