package com.ringo.rinova.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class PinkiliteStaffItem extends Item {
    public PinkiliteStaffItem(Properties properties) {
        super(properties
                .stacksTo(1)
                .durability(100)
                .rarity(Rarity.UNCOMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.REGENERATION,
                    100,
                    1,
                    false,
                    true
            ));
            stack.hurtAndBreak(1, player, (entity) -> {
                entity.broadcastBreakEvent(hand);
            });
            player.getCooldowns().addCooldown(this, 20);
        } else {
            player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.0F);
        }
        return InteractionResultHolder.success(stack);
    }
}
