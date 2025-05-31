package com.ringo.rinova.core.datagen.providers;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.core.registry.RBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class RBlockStateProvider extends BlockStateProvider {
    public RBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RinovaMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(RBlocks.PINKYLITE_CRYSTAL_BLOCK);
        blockWithItem(RBlocks.PINKYLITE_ORE);
        blockWithItem(RBlocks.DEEPSLATE_PINKYLITE_ORE);
        blockWithItem(RBlocks.ELEMENTAL_TRAP);
        blockWithItem(RBlocks.SPRING_TRAP);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
