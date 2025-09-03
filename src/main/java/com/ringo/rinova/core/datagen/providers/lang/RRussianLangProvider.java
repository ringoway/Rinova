package com.ringo.rinova.core.datagen.providers.lang;

import com.ringo.rinova.RinovaMod;
import com.ringo.rinova.core.registry.RBlocks;
import com.ringo.rinova.core.registry.RItems;
import net.minecraft.data.PackOutput;

public class RRussianLangProvider extends RLangProvider {
    public RRussianLangProvider(PackOutput output) {
        super(output, RinovaMod.MOD_ID, "ru_ru");
    }

    @Override
    protected void addTranslations() {
        // Творческая вкладка
        addCreativeTab("tab", "Rinova");

        // Предметы
        addItem(RItems.PINKYLITE_CRYSTAL, "Пинкилитовый кристалл");
        addItem(RItems.PINKYLITE_CRYSTAL_FRAGMENT, "Осколок пинкилитового кристалла");
        addItem(RItems.VULTAN_RODS, "Вултановый стержень");
        addItem(RItems.HELL_BRUSH, "Адская кисточка");
        addItem(RItems.CRYSTAL_GRACE, "Кристалл благодати");

        //Еда
        addItem(RItems.RAW_GOAT_MEAT, "Сырая козлятина");
        addItem(RItems.COOKED_GOAT_MEAT, "Жареная козлятина");
        addItem(RItems.PINKYLITE_CARROT, "Пинкилитовая морковь");
        addItem(RItems.HEART_WITHER, "Сердце Иссушителя");

        // Предмет с подсказкой
        addItem(RItems.PINKYLITE_STUFF, "Пинкилитовый посох");
        addTooltip(RItems.PINKYLITE_STUFF.get(), "Магия регенерации");
        addTooltipWithKey("item.rinova.pinkylite_stuff", "charges", "Заряды: %d");

        // Блоки
        addBlock(RBlocks.PINKYLITE_CRYSTAL_BLOCK, "Пинкилитовый кристальный блок");
        addBlock(RBlocks.PINKYLITE_ORE, "Пинкилитовая руда");
        addBlock(RBlocks.DEEPSLATE_PINKYLITE_ORE, "Глубинная пинкилитовая руда");
        addBlock(RBlocks.ELEMENTAL_TRAP, "Элементальная ловушка");
        addBlock(RBlocks.SPRING_TRAP, "Пружинная ловушка");

        // Инструменты
        addItem(RItems.PINKYLITE_SWORD, "Пинкилитовый меч");
        addItem(RItems.PINKYLITE_PICKAXE, "Пинкилитовая кирка");
        addItem(RItems.PINKYLITE_AXE, "Пинкилитовый топор");
        addItem(RItems.PINKYLITE_SHOVEL, "Пинкилитовая лопата");
        addItem(RItems.PINKYLITE_HOE, "Пинкилитовая мотыга");

        // Броня и шаблон
        addItem(RItems.PINKYLITE_HELMET, "Пинкилитовый шлем");
        addItem(RItems.PINKYLITE_CHESTPLATE, "Пинкилитовый нагрудник");
        addItem(RItems.PINKYLITE_LEGGINGS, "Пинкилитовые поножи");
        addItem(RItems.PINKYLITE_BOOTS, "Пинкилитовые ботинки");

        addSmithingTemplate(RItems.PINKYLITE_UPGRADE_SMITHING_TEMPLATE.get(),
                "Пинкилитовое улучшение",
                "Алмазная экипировка",
                "Пинкилитовый кристальный блок",
                "Добавьте алмазную броню, оружие или инструмент",
                "Добавьте Пинкилитовый кристальный блок");
    }
}