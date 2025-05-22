package com.ringo.rinova.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PinkiliteStaffItem extends Item {

    public PinkiliteStaffItem(Properties properties) {
        super(properties
                .stacksTo(1)
                .durability(100)
                .rarity(Rarity.UNCOMMON));
    }

    @OnlyIn(Dist.CLIENT) // Работает только на клиенте!
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        // Просто текст
        tooltip.add(Component.translatable("item.rinova.pinkylite_stuff.tooltip") // Ключ локализации
                .withStyle(color -> color.withColor(13806279))
                .withStyle(ChatFormatting.ITALIC));

        // Просто пробел в тексте
        tooltip.add(Component.empty());

        // Можно вывести эффект, локализация учитывается
        String effectName = MobEffects.REGENERATION.getDescriptionId(); //получим айди эффекта
        tooltip.add(Component.translatable(effectName) //используем ключ эффекта в нашем списке Tooltip
                .withStyle(ChatFormatting.LIGHT_PURPLE));

        // Покажет оставшееся "заряды"
        int max = stack.getMaxDamage(); // Максимальная прочность
        int currentDamage = stack.getDamageValue(); // Текущая прочность
        int remaining = max - currentDamage; // Оставшаяся прочность
        tooltip.add(Component.translatable("item.rinova.pinkylite_stuff.tooltip.charges", remaining)
                .withStyle(ChatFormatting.GRAY)
                .withStyle(ChatFormatting.UNDERLINE));
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
