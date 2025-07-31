package com.ringo.rinova.common.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class RFoods {
    public static final FoodProperties RAW_GOAT_MEAT = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.3F)
            .meat()
            .build();
    public static final FoodProperties COOKED_GOAT_MEAT = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.8F)
            .meat()
            .build();
    public static final FoodProperties PINKYLITE_CARROT = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(1.6F)
            .alwaysEat()
            .fast()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 120, 2), 1.0f)
            .build();
    public static final FoodProperties HEART_WITHER = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(1.0f)
            .meat()
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 200, 2), 0.9f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 4), 1.0f)
            .build();
}
