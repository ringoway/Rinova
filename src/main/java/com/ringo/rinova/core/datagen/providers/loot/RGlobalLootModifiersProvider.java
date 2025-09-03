package com.ringo.rinova.core.datagen.providers.loot;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.common.loot.AddItemModifier;
import com.ringo.rinova.core.registry.RItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class RGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public RGlobalLootModifiersProvider(PackOutput output) {
        super(output, RinovaMod.MOD_ID);
    }

    @Override
    protected void start() {
        // Добавляем Hell Brush в сундуки бастиона
        add("hell_brush_from_bastion",
                new AddItemModifier(
                        new LootItemCondition[] {
                                LootTableIdCondition.builder(
                                        new ResourceLocation("minecraft:chests/bastion_treasure")
                                ).build()
                        },
                        RItems.HELL_BRUSH.get()
                )
        );

        // Добавляем сердце иссушителя с босса
        add("wither_heart_from_wither",
                new AddItemModifier(
                        new LootItemCondition[] {
                                LootTableIdCondition.builder(
                                        new ResourceLocation("minecraft:entities/wither")
                                ).build(),
                                LootItemKilledByPlayerCondition.killedByPlayer().build()
                        },
                        RItems.HEART_WITHER.get()
                )
        );

        // Мясо козы с козы
        add("raw_goat_meat_from_goat",
                new AddItemModifier(
                        new LootItemCondition[] {
                                LootTableIdCondition.builder(EntityType.GOAT.getDefaultLootTable()).build()
                        },
                        RItems.RAW_GOAT_MEAT.get()
                )
        );

        add("pinkylite_template_from_stronghold_library",
                new AddItemModifier(
                        new LootItemCondition[] {
                                LootTableIdCondition.builder(
                                        new ResourceLocation("minecraft:chests/stronghold_library")
                                ).build()
                        },
                        RItems.PINKYLITE_UPGRADE_SMITHING_TEMPLATE.get()
                )
        );

        add("pinkylite_horse_armor_from_nether_bridge",
                new AddItemModifier(
                        new LootItemCondition[] {
                                LootTableIdCondition.builder(
                                        new ResourceLocation("minecraft:chests/nether_bridge")
                                ).build(),
                                LootItemRandomChanceCondition.randomChance(0.2f).build()
                        },
                        RItems.PINKYLITE_HORSE_ARMOR.get()
                )
        );
    }
}
