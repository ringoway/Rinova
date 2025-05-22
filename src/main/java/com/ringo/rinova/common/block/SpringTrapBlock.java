package com.ringo.rinova.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SpringTrapBlock extends Block {
    public SpringTrapBlock() {
        super(BlockBehaviour.Properties
                .copy(Blocks.IRON_BLOCK)
                .strength(2.0f)
                .sound(SoundType.METAL));
    }
    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        // Отмена стандартного урона для всех сущностей
        entity.causeFallDamage(fallDistance, 0.0f, level.damageSources().fall());

        // Логика только на сервере
        if (!level.isClientSide) {
            // Для игрока: добавляем эффект сопротивления
            if (entity instanceof Player player) {
                int resistanceLevel = Math.min((int) (fallDistance / 20), 5); // Уровень = высота / 20 (макс. 5)
                player.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_RESISTANCE,
                        200, // 10 секунд (200 тиков)
                        resistanceLevel - 1 // Уровень начинается с 0 (I уровень = 0)
                ));
            }

            // Для монстров: увеличиваем урон в 4 раза
            if (entity instanceof Monster monster) {
                float baseDamage = (fallDistance - 1) * 2; // стандартный урон
                float multipliedDamage = baseDamage * 4; // x4
                monster.hurt(level.damageSources().fall(), multipliedDamage);
            }
        }

        // Спавн частиц и звука (только на клиенте)
        if (level.isClientSide && entity instanceof Player) {
            spawnResistanceParticles(level, pos);
            level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE,
                    SoundSource.BLOCKS, 0.8f, 1.0f, false);
        }

        super.fallOn(level, state, pos, entity, fallDistance);
    }

    // Спавн частиц
    private void spawnResistanceParticles(Level level, BlockPos pos) {
        RandomSource rand = level.getRandom();
        for (int i = 0; i < 15; ++i) {
            double x = pos.getX() + rand.nextDouble();
            double y = pos.getY() + 1.2;
            double z = pos.getZ() + rand.nextDouble();
            level.addParticle(ParticleTypes.ENCHANT, x, y, z, 0, 0.1, 0);
        }
    }
}
