package com.ringo.rinova.core.datagen.providers.loot;

import com.ringo.rinova.core.registry.RBlocks;
import com.ringo.rinova.core.registry.RItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RBlockLootProvider extends BlockLootSubProvider {
    public RBlockLootProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(RBlocks.ELEMENTAL_TRAP.get());
        dropSelf(RBlocks.SPRING_TRAP.get());
        dropSelf(RBlocks.PINKYLITE_CRYSTAL_BLOCK.get());

        addOreDrop(RBlocks.DEEPSLATE_PINKYLITE_ORE.get(), RItems.PINKYLITE_CRYSTAL.get(), 1f, 2f);
        addOreDrop(RBlocks.PINKYLITE_ORE.get(), RItems.PINKYLITE_CRYSTAL.get(), 1f, 2f);
    }

    private void addOreDrop(Block oreBlock, Item drop, float min, float max) {
        add(oreBlock, createSilkTouchDispatchTable(oreBlock,
                applyExplosionDecay(oreBlock,
                        LootItem.lootTableItem(drop)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Stream.of(
                        RBlocks.BLOCKS.getEntries().stream() // Тут наш класс блоков!
                )
                .flatMap(Function.identity()) // Объединяем все потоки в один
                .map(RegistryObject::get)
                .collect(Collectors.toList());
    }
}
