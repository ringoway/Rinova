package com.ringo.rinova.core.registry;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.common.food.RFoods;
import com.ringo.rinova.common.item.CrystalGraceItem;
import com.ringo.rinova.common.item.HellBrushItem;
import com.ringo.rinova.common.item.PinkiliteStaffItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RinovaMod.MOD_ID);

    public static final RegistryObject<Item> PINKYLITE_CRYSTAL = registerSimpleItem("pinkylite_crystal");
    public static final RegistryObject<Item> PINKYLITE_CRYSTAL_FRAGMENT = registerSimpleItem("pinkylite_crystal_fragment");
    public static final RegistryObject<Item> VULTAN_RODS = registerSimpleItem("vultan_rods");
    public static final RegistryObject<Item> PINKYLITE_STUFF = registerItem("pinkylite_stuff",
            () -> new PinkiliteStaffItem(new Item.Properties()));
    public static final RegistryObject<Item> HELL_BRUSH = registerItem("hell_brush",
            () -> new HellBrushItem(new Item.Properties()));
    public static final RegistryObject<Item> CRYSTAL_GRACE = registerItem("crystal_of_grace",
            () -> new CrystalGraceItem(new Item.Properties()));

    // Food
    public static final RegistryObject<Item> RAW_GOAT_MEAT = registerFoodItem("raw_goat_meat", RFoods.RAW_GOAT_MEAT);
    public static final RegistryObject<Item> COOKED_GOAT_MEAT = registerFoodItem("cooked_goat_meat", RFoods.COOKED_GOAT_MEAT);
    public static final RegistryObject<Item> PINKYLITE_CARROT = registerFoodItem("pinkylite_carrot", RFoods.PINKYLITE_CARROT);
    public static final RegistryObject<Item> HEART_WITHER = registerFoodItem("heart_wither", RFoods.HEART_WITHER);

    private static <T extends Item> RegistryObject<T> registerItem(String id, Supplier<T> item) {
        return ITEMS.register(id, item);
    }

    private static RegistryObject<Item> registerSimpleItem(String id) {
        return ITEMS.register(id, () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<Item> registerFoodItem(String id, FoodProperties foodProperties) {
        return ITEMS.register(id, () -> new Item(new Item.Properties().food(foodProperties)));
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
