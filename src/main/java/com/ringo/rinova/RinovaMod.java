package com.ringo.rinova;

import com.ringo.rinova.core.registry.RBlocks;
import com.ringo.rinova.core.registry.RCreativeTabs;
import com.ringo.rinova.core.registry.RItems;
import com.ringo.rinova.core.registry.RLootModifiers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RinovaMod.MOD_ID)
public class RinovaMod {
    public static final String MOD_ID = "rinova";

    public RinovaMod(FMLJavaModLoadingContext context) {
        //Стало:
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);

        //Регистрация предметов
        RItems.register(modEventBus);
        //Регистрация блоков
        RBlocks.register(modEventBus);
        //Регистрация вкладок
        RCreativeTabs.register(modEventBus);
        //Регистрация лут модификаторов
        RLootModifiers.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
