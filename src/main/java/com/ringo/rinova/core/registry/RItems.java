package com.ringo.rinova.core.registry;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.common.item.HellBrushItem;
import com.ringo.rinova.common.item.PinkiliteStaffItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RinovaMod.MOD_ID);

    public static final RegistryObject<Item> PINKYLITE_CRYSTAL = registerSimpleItem("pinkylite_crystal");

    public static final RegistryObject<Item> VULTAN_RODS = registerSimpleItem("vultan_rods");

    public static final RegistryObject<Item> PINKYLITE_STUFF = registerItem("pinkylite_stuff",
            () -> new PinkiliteStaffItem(new Item.Properties()));
    public static final RegistryObject<Item> HELL_BRUSH = registerItem("hell_brush",
            () -> new HellBrushItem(new Item.Properties()));

    private static <T extends Item> RegistryObject<T> registerItem(String id, Supplier<T> item) {
        return ITEMS.register(id, item);
    }
    private static RegistryObject<Item> registerSimpleItem(String id) {
        return ITEMS.register(id, () -> new Item(new Item.Properties()));
    }
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
