package com.ringo.rinova.core.datagen.providers.lang;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.core.registry.RBlocks;
import com.ringo.rinova.core.registry.RItems;
import net.minecraft.data.PackOutput;

public class REnglishLangProvider extends RLangProvider {
    public REnglishLangProvider(PackOutput output) {
        super(output, RinovaMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Творческая вкладка
        addCreativeTab("tab", "Rinova");

        // Предметы
        addItem(RItems.PINKYLITE_CRYSTAL, "Pinkylite crystal");
        addItem(RItems.PINKYLITE_CRYSTAL_FRAGMENT, "Pinkylite crystal fragment");
        addItem(RItems.VULTAN_RODS, "Vultan rods");
        addItem(RItems.HELL_BRUSH, "Hell brush");
        addItem(RItems.CRYSTAL_GRACE, "Crystal of grace");

        // Еда
        addItem(RItems.RAW_GOAT_MEAT, "Raw goat meat");
        addItem(RItems.COOKED_GOAT_MEAT, "Cooked goat meat");
        addItem(RItems.PINKYLITE_CARROT, "Pinkylite carrot");
        addItem(RItems.HEART_WITHER, "Heart of the Wither");

        // Предмет с подсказкой
        addItem(RItems.PINKYLITE_STUFF, "Pinkylite stuff");
        addTooltip(RItems.PINKYLITE_STUFF.get(), "Regeneration magic");
        addTooltipWithKey("item.rinova.pinkylite_stuff", "charges", "Charges: %d");

        // Блоки
        addBlock(RBlocks.PINKYLITE_CRYSTAL_BLOCK, "Pinkylite crystal block");
        addBlock(RBlocks.PINKYLITE_ORE, "Pinkylite ore");
        addBlock(RBlocks.DEEPSLATE_PINKYLITE_ORE, "Deepslate pinkylite ore");
        addBlock(RBlocks.ELEMENTAL_TRAP, "Elemental trap");
        addBlock(RBlocks.SPRING_TRAP, "Spring trap");

        // Инструменты
        addItem(RItems.PINKYLITE_SWORD, "Pinkylite sword");
        addItem(RItems.PINKYLITE_PICKAXE, "Pinkylite pickaxe");
        addItem(RItems.PINKYLITE_AXE, "Pinkylite axe");
        addItem(RItems.PINKYLITE_SHOVEL, "Pinkylite shovel");
        addItem(RItems.PINKYLITE_HOE, "Pinkylite how");
    }
}
