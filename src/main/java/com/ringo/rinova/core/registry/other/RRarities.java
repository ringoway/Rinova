package com.ringo.rinova.core.registry.other;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Rarity;

import java.awt.*;

public class RRarities {
    public static final Rarity AMBER = Rarity.create("amber",
            style -> style.withColor(TextColor.parseColor("#FFAF00")));
    public static final Rarity RAINBOW = Rarity.create("rainbow", style -> {
        float hue = (System.currentTimeMillis() % 10000L) / 10000.0f;
        int rgb = Color.HSBtoRGB(hue, 1.0f, 1.0f);
        return style.withColor(TextColor.fromRgb(rgb));
    });
}
