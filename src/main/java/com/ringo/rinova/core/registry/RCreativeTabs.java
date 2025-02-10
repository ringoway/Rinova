package com.ringo.rinova.core.registry;

import com.ringo.rinova.RinovaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RinovaMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab
            .builder()
            .icon(() -> RItems.PINKYLITE_CRYSTAL.get().getDefaultInstance())
            .title(Component.translatable("creative_tab.rinova.tab"))
            .displayItems((parameters, output) -> {
                RItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
            }).build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}