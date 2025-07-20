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
        addItem(RItems.VULTAN_RODS, "Вултановый стержень");
        addItem(RItems.HELL_BRUSH, "Адская кисточка");
        addItem(RItems.CRYSTAL_GRACE, "Кристалл благодати");
        addItem(RItems.HEART_WITHER, "Сердце Иссушителя");

        // Предмет с подсказкой
        addItem(RItems.PINKYLITE_STUFF, "Пинкилитовый посох");
        addTooltip(RItems.PINKYLITE_STUFF.get(), "Магия регенерации");
        addTooltipWithKey("item.rinova.pinkylite_stuff", "charges", "Заряды: %d");

        // Блоки
        addBlock(RBlocks.PINKYLITE_CRYSTAL_BLOCK, "Пинкилитовый кристалльный блок");
        addBlock(RBlocks.PINKYLITE_ORE, "Пинкилитовая руда");
        addBlock(RBlocks.DEEPSLATE_PINKYLITE_ORE, "Глубинная пинкилитовая руда");
        addBlock(RBlocks.ELEMENTAL_TRAP, "Элементальная ловушка");
        addBlock(RBlocks.SPRING_TRAP, "Пружинная ловушка");
    }
}