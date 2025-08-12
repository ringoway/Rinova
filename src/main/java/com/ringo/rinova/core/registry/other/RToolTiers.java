package com.ringo.rinova.core.registry.other;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.core.registry.RItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class RToolTiers {
    public static final Tier PINKYLITE_CRYSTAL = TierSortingRegistry.registerTier(
            new ForgeTier(3, 1787, 8.5f, 3.5f, 15,
                    BlockTags.NEEDS_DIAMOND_TOOL,
                    () -> Ingredient.of(RItems.PINKYLITE_CRYSTAL.get())),
            new ResourceLocation(RinovaMod.MOD_ID, "pinkylite_crystal"),
            List.of(Tiers.DIAMOND), // После каких материалов идет
            List.of(Tiers.NETHERITE)); // Перед какими материалами идет
}
