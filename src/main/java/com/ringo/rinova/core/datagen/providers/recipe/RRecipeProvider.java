package com.ringo.rinova.core.datagen.providers.recipe;

import com.google.common.base.Preconditions;
import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.core.registry.RBlocks;
import com.ringo.rinova.core.registry.RItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class RRecipeProvider extends RecipeProvider {
    public RRecipeProvider(PackOutput output) {
        super(output);
    }
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RItems.VULTAN_RODS.get(), 6)
                .pattern("bo") // Шаблон строк
                .pattern("bo")
                .pattern("bo")
                .define('b', Blocks.BASALT) // Символы в шаблоне
                .define('o', Blocks.OBSIDIAN)
                .unlockedBy(getHasName(Blocks.BASALT), has(Blocks.BASALT)) // Условие разблокировки
                .save(writer, ResourceLocation.fromNamespaceAndPath( // rinova/recipes/crafting/рецепт.json
                        RinovaMod.MOD_ID, "crafting/" + getItemName(RItems.VULTAN_RODS.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RItems.HELL_BRUSH.get(), 1)
                .pattern("f")
                .pattern("d")
                .pattern("v")
                .define('f', Items.FEATHER)
                .define('d', Items.DIAMOND)
                .define('v', RItems.VULTAN_RODS.get())
                .unlockedBy(getHasName(RItems.VULTAN_RODS.get()), has(RItems.VULTAN_RODS.get()))
                .save(writer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "crafting/" + getItemName(RItems.HELL_BRUSH.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RBlocks.ELEMENTAL_TRAP.get(), 1)
                .pattern("mam")
                .pattern("apa")
                .pattern("mam")
                .define('a', Items.GOLDEN_APPLE)
                .define('m', Items.MAGMA_CREAM)
                .define('p', RBlocks.PINKYLITE_CRYSTAL_BLOCK.get())
                .unlockedBy(getHasName(RBlocks.PINKYLITE_CRYSTAL_BLOCK.get()), has(RBlocks.PINKYLITE_CRYSTAL_BLOCK.get()))
                .save(writer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "crafting/" + getItemName(RBlocks.ELEMENTAL_TRAP.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RItems.PINKYLITE_STUFF.get(), 1)
                .pattern("  p")
                .pattern(" v ")
                .pattern("v  ")
                .define('v', RItems.VULTAN_RODS.get())
                .define('p', RItems.PINKYLITE_CRYSTAL.get())
                .unlockedBy(getHasName(RItems.PINKYLITE_CRYSTAL.get()), has(RItems.PINKYLITE_CRYSTAL.get()))
                .save(writer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "crafting/" + getItemName(RItems.PINKYLITE_STUFF.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RBlocks.SPRING_TRAP.get(), 1)
                .pattern("scs")
                .pattern("dpd")
                .pattern("scs")
                .define('s', Items.SLIME_BALL)
                .define('c', Items.SCUTE)
                .define('d', Items.POINTED_DRIPSTONE)
                .define('p', RBlocks.PINKYLITE_CRYSTAL_BLOCK.get())
                .unlockedBy(getHasName(RBlocks.PINKYLITE_CRYSTAL_BLOCK.get()), has(RBlocks.PINKYLITE_CRYSTAL_BLOCK.get()))
                .save(writer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "crafting/" + getItemName(RBlocks.SPRING_TRAP.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RItems.PINKYLITE_CARROT.get(), 1)
                .pattern("ppp")
                .pattern("pcp")
                .pattern("ppp")
                .define('p', RItems.PINKYLITE_CRYSTAL_FRAGMENT.get())
                .define('c', Items.GOLDEN_CARROT)
                .unlockedBy(getHasName(RItems.PINKYLITE_CRYSTAL_FRAGMENT.get()), has(RItems.PINKYLITE_CRYSTAL_FRAGMENT.get()))
                .save(writer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "crafting/" + getItemName(RItems.PINKYLITE_CARROT.get())));

        SimpleCookingRecipeBuilder.smelting( //билдер для создания плавки
                Ingredient.of(RBlocks.PINKYLITE_ORE.get(), RBlocks.DEEPSLATE_PINKYLITE_ORE.get()), //входящие ингредиенты
                        RecipeCategory.MISC,
                        RItems.PINKYLITE_CRYSTAL.get(),
                        2.5f, 200) // Опыт и время
                .unlockedBy("has_ore", has(RBlocks.PINKYLITE_ORE.get()))
                .save(writer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "smelting/" + getItemName(RItems.PINKYLITE_CRYSTAL.get())));

        foodCooking(writer, RItems.RAW_GOAT_MEAT.get(), RItems.COOKED_GOAT_MEAT.get()); // Супер удобно!

        compat(writer, RItems.PINKYLITE_CRYSTAL.get(), RBlocks.PINKYLITE_CRYSTAL_BLOCK.get());
        compat(writer, RItems.PINKYLITE_CRYSTAL_FRAGMENT.get(), RItems.PINKYLITE_CRYSTAL.get());
    }
    protected static void foodCooking(Consumer<FinishedRecipe> writer, Item input, Item result) {
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(input),
                        RecipeCategory.FOOD,
                        result,
                        0.35F, 200)
                .unlockedBy("has_" + getItemName(input), has(input))
                .save(writer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "smelting/" + getItemName(result)));

        SimpleCookingRecipeBuilder.smoking(
                        Ingredient.of(input),
                        RecipeCategory.FOOD,
                        result,
                        0.35F, 100)
                .unlockedBy("has_" + getItemName(input), has(input))
                .save(writer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "smoking/" + getItemName(result)));

        SimpleCookingRecipeBuilder.campfireCooking(
                        Ingredient.of(input),
                        RecipeCategory.FOOD,
                        result,
                        0.35F, 600)
                .unlockedBy("has_" + getItemName(input), has(input))
                .save(writer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "campfire_cooking/" + getItemName(result)));
    }

    protected static void compat(Consumer<FinishedRecipe> consumer, ItemLike input, ItemLike result) {
        // Рецепт блока из предметов
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern("ccc")
                .pattern("ccc")
                .pattern("ccc")
                .define('c', input)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "crafting/compact/" + getItemName(input) + "_from_" + getItemName(result)));

        // Рецепт распаковки
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, input, 9)
                .requires(result)
                .unlockedBy(getHasName(result), has(result))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(
                        RinovaMod.MOD_ID, "crafting/uncompact/" + getItemName(result) + "_from_" + getItemName(input)));
    }
}
