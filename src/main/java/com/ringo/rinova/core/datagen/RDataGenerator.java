package com.ringo.rinova.core.datagen;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.core.datagen.providers.lang.REnglishLangProvider;
import com.ringo.rinova.core.datagen.providers.lang.RRussianLangProvider;
import com.ringo.rinova.core.datagen.providers.loot.RBlockLootProvider;
import com.ringo.rinova.core.datagen.providers.loot.RGlobalLootModifiersProvider;
import com.ringo.rinova.core.datagen.providers.models.RBlockStateProvider;
import com.ringo.rinova.core.datagen.providers.models.RItemModelProvider;
import com.ringo.rinova.core.datagen.providers.recipe.RRecipeProvider;
import com.ringo.rinova.core.datagen.providers.tags.RBlockTagsProvider;
import com.ringo.rinova.core.datagen.providers.tags.RItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;
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

        // Перевод языков (Языковые провайдеры - тоже клиентские!)
        generator.addProvider(true, new REnglishLangProvider(packOutput));
        generator.addProvider(true, new RRussianLangProvider(packOutput));
    }

    // Тут серверные генераторы данных
    private static void addServerProviders(DataGenerator generator, PackOutput packOutput,
                                           CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        // Генерация рецептов
        generator.addProvider(true, new RRecipeProvider(packOutput));

        // Генерация лут-таблиц
        generator.addProvider(true, new LootTableProvider(packOutput, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(RBlockLootProvider::new, LootContextParamSets.BLOCK))));

        // Генерация глобальных модификаторов лута
        generator.addProvider(true, new RGlobalLootModifiersProvider(packOutput));

        // Генерация тегов блоков и предметов
        RBlockTagsProvider blockTagGenerator = generator.addProvider(true,
                new RBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(true,
                new RItemTagsProvider(packOutput, lookupProvider,
                        blockTagGenerator.contentsGetter(), existingFileHelper));
    }
}
