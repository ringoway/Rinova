package com.ringo.rinova.core.datagen.providers.models;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.core.registry.RItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class RItemModelProvider extends ItemModelProvider {
    public RItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RinovaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(RItems.PINKYLITE_CRYSTAL.get());
        basicItem(RItems.VULTAN_RODS.get());
        basicItem(RItems.CRYSTAL_GRACE.get());

        handheldItem(RItems.PINKYLITE_STUFF);
        handheldItem(RItems.HELL_BRUSH);

        basicItem(RItems.HEART_WITHER.get());
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(RinovaMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
