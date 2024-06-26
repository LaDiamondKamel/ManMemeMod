package com.mememan.mememanmod.datagen;

import com.mememan.mememanmod.ManMemeMod;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.function.Consumer;

public class MMMRecipeProvider extends RecipeProvider {

    public MMMRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
    }

    protected static void nuggetToIngot(Consumer<FinishedRecipe> recipeConsumer, ItemLike pIngot, ItemLike pNugget) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pIngot)
                .define('N', pNugget)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .unlockedBy("has_" + pNugget.asItem(), has(pNugget))
                .save(recipeConsumer, ManMemeMod.MODID + ":" + getItemName(pIngot) + "_from_nuggets");
    }

    protected static void ingotToNugget(Consumer<FinishedRecipe> recipeConsumer, ItemLike pIngot, ItemLike pNugget) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, pNugget, 9)
                .requires(pIngot)
                .unlockedBy("has_" + getItemName(pIngot.asItem()), has(pIngot))
                .save(recipeConsumer);
    }

    protected static void materialToBlock(Consumer<FinishedRecipe> recipeConsumer, ItemLike pBlock, ItemLike pMaterial) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, pBlock)
                .define('#', pMaterial)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_" + getItemName(pMaterial.asItem()), has(pMaterial))
                .save(recipeConsumer, ManMemeMod.prefix(getItemName(pMaterial) + "_to_" + getItemName(pBlock)));
    }

    protected static void blockToMaterial(Consumer<FinishedRecipe> recipeConsumer, ItemLike pBlock, ItemLike pMaterial) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, pMaterial, 9)
                .requires(pBlock)
                .unlockedBy("has_" + getItemName(pBlock), has(pBlock))
                .save(recipeConsumer, ManMemeMod.prefix(getItemName(pBlock) + "_to_" + getItemName(pMaterial)));
    }

    protected static void toolSword(Consumer<FinishedRecipe> recipeConsumer, ItemLike pToolSword, ItemLike pMaterial) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pToolSword)
                .define('S', Items.STICK)
                .define('#', pMaterial)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .unlockedBy("has_" + getItemName(pMaterial.asItem()), has(pMaterial))
                .save(recipeConsumer);
    }

    protected static void specialHelmet(Consumer<FinishedRecipe> recipeConsumer, ItemLike helmetItem, ItemLike discItem, ItemLike ingotItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmetItem)
                .define('D', discItem)
                .define('I', ingotItem)
                .pattern("III")
                .pattern("IDI")
                .unlockedBy("has_" + getItemName(discItem), has(discItem))
                .save(recipeConsumer);
    }

    protected static void specialChestplate(Consumer<FinishedRecipe> recipeConsumer, ItemLike chestplateItem, ItemLike discItem, ItemLike ingotItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplateItem)
                .define('D', discItem)
                .define('I', ingotItem)
                .pattern("I I")
                .pattern("IDI")
                .pattern("III")
                .unlockedBy("has_" + getItemName(discItem), has(discItem))
                .save(recipeConsumer);
    }

    protected static void specialLeggings(Consumer<FinishedRecipe> recipeConsumer, ItemLike leggingsItem, ItemLike discItem, ItemLike ingotItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggingsItem)
                .define('D', discItem)
                .define('I', ingotItem)
                .pattern("III")
                .pattern("IDI")
                .pattern("I I")
                .unlockedBy("has_" + getItemName(discItem), has(discItem))
                .save(recipeConsumer);
    }

    protected static void specialBoots(Consumer<FinishedRecipe> recipeConsumer, ItemLike bootsItem, ItemLike discItem, ItemLike ingotItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, bootsItem)
                .define('D', discItem)
                .define('I', ingotItem)
                .pattern("I I")
                .pattern("IDI")
                .unlockedBy("has_" + getItemName(discItem), has(discItem))
                .save(recipeConsumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, ManMemeMod.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}


