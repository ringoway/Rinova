package com.ringo.rinova.core.datagen.providers.tags;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.core.registry.RBlocks;
import com.ringo.rinova.core.tags.RBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RBlockTagsProvider extends BlockTagsProvider {
    public RBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RinovaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        registerMineableTags();
        registerToolRequirementTags();

        tag(RBlockTags.STONE_ROCKS)
                .add(Blocks.STONE)
                .add(Blocks.GRANITE)
                .add(Blocks.ANDESITE)
                .add(Blocks.DIORITE);
    }

    private void registerMineableTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(RBlocks.PINKYLITE_CRYSTAL_BLOCK.get())
                .add(RBlocks.PINKYLITE_ORE.get())
                .add(RBlocks.DEEPSLATE_PINKYLITE_ORE.get())
                .add(RBlocks.ELEMENTAL_TRAP.get())
                .add(RBlocks.SPRING_TRAP.get());

        // Блоки, добываемые другими инструментами:
//        tag(BlockTags.MINEABLE_WITH_SHOVEL)
//                .add();
//        tag(BlockTags.MINEABLE_WITH_AXE)
//                .add();
//        tag(BlockTags.MINEABLE_WITH_HOE)
//                .add();
    }

    private void registerToolRequirementTags() {
        // Для железных инструментов
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(RBlocks.PINKYLITE_ORE.get());

        // Для алмазных инструментов
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(RBlocks.PINKYLITE_CRYSTAL_BLOCK.get());

        // Для незеритовых инструментов (Forge-тег)
        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(RBlocks.DEEPSLATE_PINKYLITE_ORE.get());
    }
}
