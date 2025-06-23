package com.ringo.rinova.core.datagen;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.core.datagen.providers.models.RBlockStateProvider;
import com.ringo.rinova.core.datagen.providers.models.RItemModelProvider;
import com.ringo.rinova.core.datagen.providers.recipe.RRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = RinovaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Генерация клиентских данных
        if (event.includeClient()) {
            addClientProviders(generator, packOutput, existingFileHelper);
        }
        // Генерация серверных данных
        if (event.includeServer()) {
            addServerProviders(generator, packOutput, lookupProvider, existingFileHelper);
        }
    }

    // Тут клиентские генераторы данных
    private static void addClientProviders(DataGenerator generator, PackOutput packOutput,
                                           ExistingFileHelper existingFileHelper) {
        // Модели блоков и предметов
        generator.addProvider(true, new RBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(true, new RItemModelProvider(packOutput, existingFileHelper));
    }

    // Тут серверные генераторы данных
    private static void addServerProviders(DataGenerator generator, PackOutput packOutput,
                                           CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        generator.addProvider(true, new RRecipeProvider(packOutput));
    }
}
