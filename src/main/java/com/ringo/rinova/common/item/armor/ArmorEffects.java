package com.ringo.rinova.common.item.armor;

import com.ringo.rinova.core.registry.other.RArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmorEffects {
    private static final Map<ArmorMaterial, List<MobEffectInstance>> EFFECTS_MAP = new HashMap<>();

    static {
        registerEffects(RArmorMaterials.PINKYLITE, List.of(
                new MobEffectInstance(MobEffects.REGENERATION, 200),
                new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200)
        ));
    }

    public static void registerEffects(ArmorMaterial material, List<MobEffectInstance> effects) {
        EFFECTS_MAP.put(material, effects);
    }

    public static List<MobEffectInstance> getEffectsForMaterial(ArmorMaterial material) {
        return EFFECTS_MAP.get(material);
    }
}
