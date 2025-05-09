package com.ringo.rinova.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class HellBrushItem extends Item {
    public HellBrushItem(Properties properties) {
        super(properties
                .stacksTo(1)
                .durability(300)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        if (player == null) return InteractionResult.FAIL;

        // Проверяем блок и сторону сервера
        if (!level.isClientSide) {
            BlockState state = level.getBlockState(pos);
            InteractionHand hand = context.getHand();

            // Камень → Незерак
            if (state.is(Blocks.STONE)) {
                level.setBlock(pos, Blocks.NETHERRACK.defaultBlockState(), 3);
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                return InteractionResult.SUCCESS;
            }

            // Глубинный сланец → Базальт
            if (state.is(Blocks.DEEPSLATE)) {
                level.setBlock(pos, Blocks.BASALT.defaultBlockState(), 3);
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                return InteractionResult.SUCCESS;
            }
        } else {
            player.playSound(SoundEvents.BRUSH_GENERIC, 1.0F, 1.0F);
            for (int i = 0; i < 5; i++) {
                level.addParticle(
                        ParticleTypes.LARGE_SMOKE,
                        pos.getX() + 0.5D,
                        pos.getY() + 1D,
                        pos.getZ() + 0.5D,
                        (level.random.nextDouble() - 0.5) * 0.2,
                        0.1,
                        (level.random.nextDouble() - 0.5) * 0.2
                );
            }
        }
        return super.useOn(context);
    }
}
