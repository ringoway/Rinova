package com.ringo.rinova.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;


public class CustomSmithingTemplateItem extends SmithingTemplateItem {

    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    // Слот слитка
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");
    // Броня
    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    // Инструменты
    private static final ResourceLocation EMPTY_SLOT_HOE = new ResourceLocation("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = new ResourceLocation("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = new ResourceLocation("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");

    public CustomSmithingTemplateItem(String templateId, List<ResourceLocation> baseSlotIcons, List<ResourceLocation> additionsSlotIcons) {
        super(
                Component.translatable("item." + templateId + ".applies_to").withStyle(DESCRIPTION_FORMAT),
                Component.translatable("item." + templateId + ".ingredients").withStyle(DESCRIPTION_FORMAT),
                Component.translatable("item." + templateId).withStyle(TITLE_FORMAT),
                Component.translatable("item." + templateId + ".base_slot_description"),
                Component.translatable("item." + templateId + ".additions_slot_description"),
                baseSlotIcons,
                additionsSlotIcons
        );
    }

    public static CustomSmithingTemplateItem createEquipmentUpgradeTemplate(String templateId) {
        List<ResourceLocation> baseIcons = List.of(
                EMPTY_SLOT_HELMET, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_BOOTS,
                EMPTY_SLOT_PICKAXE, EMPTY_SLOT_AXE, EMPTY_SLOT_SHOVEL, EMPTY_SLOT_HOE, EMPTY_SLOT_SWORD
        );
        return new CustomSmithingTemplateItem(
                templateId,
                baseIcons,
                List.of(EMPTY_SLOT_INGOT)
        );
    }
}