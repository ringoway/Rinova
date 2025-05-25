package com.ringo.rinova.core.tags;

import com.ringo.rinova.RinovaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class RBlockTags {
    // Пример тега для каменных пород
    public static final TagKey<Block> STONE_ROCKS = tag("stone_rocks");

    // Вспомогательный метод для создания тегов
    private static TagKey<Block> tag(String name) {
        return BlockTags.create(
                new ResourceLocation(RinovaMod.MOD_ID, name)
        );
    }
}
