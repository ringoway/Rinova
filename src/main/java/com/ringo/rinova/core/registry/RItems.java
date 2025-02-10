package com.ringo.rinova.core.registry;

import com.ringo.rinova.RinovaMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RinovaMod.MOD_ID);

    public static final RegistryObject<Item> PINKYLITE_CRYSTAL = registerSimpleItem("pinkylite_crystal");

    private static RegistryObject<Item> registerSimpleItem(String id) {
        return ITEMS.register(id, () -> new Item(new Item.Properties()));
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
