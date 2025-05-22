package com.ringo.rinova.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectCategory;
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
    public static final MobEffectInstance EFFECT = new MobEffectInstance(MobEffects.REGENERATION,100,1);

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

        // Подсказка, где отображается сам эффект, время и уровень
        addBaseEffectInfo(tooltip, EFFECT);

        // Покажет оставшееся "заряды"
        int max = stack.getMaxDamage(); // Максимальная прочность
        int currentDamage = stack.getDamageValue(); // Текущая прочность
        int remaining = max - currentDamage; // Оставшаяся прочность
        tooltip.add(Component.translatable("item.rinova.pinkylite_stuff.tooltip.charges", remaining)
                .withStyle(ChatFormatting.GRAY)
                .withStyle(ChatFormatting.UNDERLINE));
    }

    private void addBaseEffectInfo(List<Component> tooltip, MobEffectInstance effect) {
        // 1. Форматирование времени
        String duration = formatDuration(effect.getDuration() / 20);

        // 2. Определение цвета текста
        ChatFormatting chatFormatting = ChatFormatting.BLUE;
        MobEffectCategory category = effect.getEffect().getCategory();
        if (category == MobEffectCategory.HARMFUL) {
            chatFormatting = ChatFormatting.RED;
        }

        // 3. Сборка компонента
        Component effectLine = Component.translatable(effect.getDescriptionId())
                .append(" ")
                .append(Component.literal(toRoman(effect.getAmplifier() + 1)))
                .append(" (" + duration + ")")
                .withStyle(chatFormatting);

        tooltip.add(effectLine);
    }

    // Конвертер арабских чисел в римские
    private String toRoman(int number) {
        if (number < 1 || number > 3999) return String.valueOf(number); // На случай невалидных значений

        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder roman = new StringBuilder();
        int i = 0;
        while (number > 0) {
            if (number >= arabicValues[i]) {
                roman.append(romanSymbols[i]);
                number -= arabicValues[i];
            } else {
                i++;
            }
        }
        return roman.toString();
    }

    private String formatDuration(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            player.addEffect(EFFECT);
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
