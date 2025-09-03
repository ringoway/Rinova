package com.ringo.rinova.core.datagen.providers.models;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.core.registry.RItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class RItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public RItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RinovaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(RItems.PINKYLITE_CRYSTAL.get());
        basicItem(RItems.PINKYLITE_CRYSTAL_FRAGMENT.get());
        basicItem(RItems.VULTAN_RODS.get());
        basicItem(RItems.CRYSTAL_GRACE.get());

        handheldItem(RItems.PINKYLITE_STUFF);
        handheldItem(RItems.HELL_BRUSH);

        basicItem(RItems.RAW_GOAT_MEAT.get());
        basicItem(RItems.COOKED_GOAT_MEAT.get());
        basicItem(RItems.PINKYLITE_CARROT.get());
        basicItem(RItems.HEART_WITHER.get());

        handheldItem(RItems.PINKYLITE_SWORD);
        handheldItem(RItems.PINKYLITE_PICKAXE);
        handheldItem(RItems.PINKYLITE_AXE);
        handheldItem(RItems.PINKYLITE_SHOVEL);
        handheldItem(RItems.PINKYLITE_HOE);

        basicItem(RItems.PINKYLITE_UPGRADE_SMITHING_TEMPLATE.get());
        trimmedArmorItem(RItems.PINKYLITE_HELMET);
        trimmedArmorItem(RItems.PINKYLITE_CHESTPLATE);
        trimmedArmorItem(RItems.PINKYLITE_LEGGINGS);
        trimmedArmorItem(RItems.PINKYLITE_BOOTS);
        basicItem(RItems.PINKYLITE_HORSE_ARMOR.get());
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(RinovaMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = RinovaMod.MOD_ID; // Ваш мод

        if (itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                // Определяем тип брони
                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
}
