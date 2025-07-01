package com.ringo.rinova.common.block;

import com.ringo.rinova.core.registry.RBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ElementalTrapBlock extends Block {
    public ElementalTrapBlock() {
        super(BlockBehaviour.Properties
                .copy(RBlocks.PINKYLITE_CRYSTAL_BLOCK.get())
                .speedFactor(0.8f)
                .strength(4.0f, 1200.0f)); //поправил, так как долго копалось
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        // Проверка: выполнять код раз в 1 секунду (20 тиков)
        if (entity.tickCount % 20 != 0) return;

        // Серверная логика
        if (!level.isClientSide) {
            // Проверка: является ли сущность entity - игроком (Player)
            if (entity instanceof Player player) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));
            }
            // Проверка: является ли сущность entity - монстром (Monster)
            if (entity instanceof Monster monster) {
                monster.hurt(level.damageSources().magic(), 1.0F);
                monster.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200));
                monster.setSecondsOnFire(5);
            }
        }
        // Клиентская логика
        else {
            // Проверка: является ли сущность игроком для спавна частиц сердец
            if (entity instanceof Player) {
                for (int i = 0; i < 8; ++i) {
                    level.addParticle(ParticleTypes.HEART,
                            pos.getX() + 0.5 + (Math.random() - 0.5),
                            pos.getY() + 1.1 + (double) i / 10,
                            pos.getZ() + 0.5 + (Math.random() - 0.5),
                            0.0D, 0.1D, 0.0D);
                }
            }
        }
        super.stepOn(level, pos, state, entity);
    }
}
