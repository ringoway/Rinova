package com.ringo.rinova.core.datagen.providers.lang;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public abstract class RLangProvider extends LanguageProvider {
    protected final String modId;

    public RLangProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
        this.modId = modid;
    }

    // Метод для добавления творческой вкладки
    protected void addCreativeTab(String tabId, String translate) {
        add("creative_tab." + modId + "." + tabId, translate);
    }

    // Метод для добавления подсказок к предметам
    protected void addTooltip(ItemLike item, String tooltip) {
        add(item.asItem().getDescriptionId() + ".tooltip", tooltip);
    }

    // Метод для добавления подсказок с дополнительными параметрами
    protected void addTooltipWithKey(String itemKey, String tooltipKey, String tooltip) {
        add(itemKey + ".tooltip." + tooltipKey, tooltip);
    }

    // Вспомогательный метод для получения пути предмета
    protected String getItemPath(ItemLike item) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.asItem())).getPath();
    }
}