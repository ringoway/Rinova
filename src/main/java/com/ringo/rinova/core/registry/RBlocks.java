package com.ringo.rinova.core.registry;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.common.block.ElementalTrapBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RinovaMod.MOD_ID);

    public static final RegistryObject<Block> PINKYLITE_CRYSTAL_BLOCK = registerBlock("pinkylite_crystal_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .sound(SoundType.AMETHYST)
                    .requiresCorrectToolForDrops()
                    .strength(4.0f, 5.0f)
                    .lightLevel(state -> 3)
                    .instrument(NoteBlockInstrument.BIT)));

    public static final RegistryObject<Block> PINKYLITE_ORE = registerBlock("pinkylite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(PINKYLITE_CRYSTAL_BLOCK.get())
                    .strength(3.0f), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> DEEPSLATE_PINKYLITE_ORE = registerBlock("deepslate_pinkylite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(PINKYLITE_CRYSTAL_BLOCK.get())
                    .strength(4.5f), UniformInt.of(4, 8)));

    public static final RegistryObject<Block> ELEMENTAL_TRAP = registerBlock("elemental_trap",
            ElementalTrapBlock::new);

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        registerBlockItem(name, registryObject);
        return registryObject;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        RItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
