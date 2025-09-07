package com.ringo.rinova.common.item.armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class EffectArmorItem extends ArmorItem {

    public EffectArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        // Вызываем родительскую логику
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);

        // Проверяем: серверная сторона + слот брони
        if (!level.isClientSide() && isArmorSlot(slotIndex)) {
            applyArmorEffects(player);
        }
    }

    /**
     * Проверяет, находится ли предмет в слоте брони
     * Слоты брони имеют индексы 36-39:
     * - 36: ботинки
     * - 37: поножи
     * - 38: нагрудник
     * - 39: шлем
     */
    private boolean isArmorSlot(int slotIndex) {
        return slotIndex >= 36 && slotIndex <= 39;
    }

    private void applyArmorEffects(Player player) {
        // Получаем материал текущей брони
        ArmorMaterial currentMaterial = this.getMaterial();

        // Проверяем наличие полного сета
        if (hasFullArmorSet(player, currentMaterial)) {
            applyEffectsForMaterial(player, currentMaterial);
        } else {
            removeEffectsForMaterial(player, currentMaterial);
        }
    }


    private boolean hasFullArmorSet(Player player, ArmorMaterial material) {
        // Проверяем все 4 слота брони
        for (int i = 0; i < 4; i++) {
            // Получаем предмет из слота брони
            ItemStack armorPiece = player.getInventory().armor.get(i);

            // Проверяем условия:
            // 1. Слот не пустой
            // 2. Предмет является броней
            // 3. Материал совпадает
            if (armorPiece.isEmpty() ||
                    !(armorPiece.getItem() instanceof ArmorItem) ||
                    ((ArmorItem) armorPiece.getItem()).getMaterial() != material) {
                return false; // Не полный сет
            }
        }
        return true; // Полный сет
    }

    // Применяет эффекты для указанного материала
    private void applyEffectsForMaterial(Player player, ArmorMaterial material) {
        // Получаем эффекты из ArmorEffects
        List<MobEffectInstance> effects = ArmorEffects.getEffectsForMaterial(material);

        if (effects != null) {
            for (MobEffectInstance effect : effects) {
                // Проверяем, нет ли уже такого эффекта
                if (!player.hasEffect(effect.getEffect())) {
                    // Создаем новый экземпляр эффекта и применяем
                    player.addEffect(new MobEffectInstance(effect));
                }
            }
        }
    }

    // Удаляет эффекты для указанного материала
    private void removeEffectsForMaterial(Player player, ArmorMaterial material) {
        // Получаем эффекты из ArmorEffects
        List<MobEffectInstance> effects = ArmorEffects.getEffectsForMaterial(material);

        if (effects != null) {
            for (MobEffectInstance effect : effects) {
                // Удаляем эффект у игрока
                player.removeEffect(effect.getEffect());
            }
        }
    }
}