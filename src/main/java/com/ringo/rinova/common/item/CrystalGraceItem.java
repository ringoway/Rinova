package com.ringo.rinova.common.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

import java.util.List;

public class CrystalGraceItem extends Item {
    private static final List<MobEffect> EFFECTS = List.of(
            MobEffects.REGENERATION,        // Индекс 0
            MobEffects.DAMAGE_RESISTANCE,   // Индекс 1
            MobEffects.DAMAGE_BOOST,        // Индекс 2
            MobEffects.HEALTH_BOOST,        // Индекс 3
            MobEffects.ABSORPTION           // Индекс 4
    );

    public CrystalGraceItem(Properties properties) {
        super(properties
                .stacksTo(1)
                .rarity(Rarity.EPIC));
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        // Проверка: предмет в активной руке или второй руке
        if (slotIndex != selectedIndex && slotIndex != 40) return;

        // Проверка: выполнять код раз в 2 секунды (40 тиков)
        if (player.tickCount % 40 != 0) return;

        // Серверная логика
        if (!level.isClientSide) {
            int numberEffect = level.random.nextInt(5);
            MobEffect mobEffect = EFFECTS.get(numberEffect);
            int amplifier = level.random.nextInt(3);
            player.addEffect(new MobEffectInstance(mobEffect,
                    40, amplifier, false, true));
        }
        // Клиентская логика
        else {
            player.playSound(SoundEvents.AMETHYST_BLOCK_HIT, 1.0f, 1.0f);
            player.level().addParticle(ParticleTypes.HAPPY_VILLAGER,
                    player.getX(), player.getY() + 2, player.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }
}