package net.craqbois.craq.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ThickFluidBucketItem extends MilkBucketItem {

    public ThickFluidBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
            stack.decrement(1);
        }

        if (!world.isClient) {
            // Duration in ticks, 1 second == 20 ticks
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 10 * 20));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 10 * 20));
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.BUCKET);
        }

        return stack;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.craq.thick_fluid_bucket.tooltip1").formatted(Formatting.DARK_PURPLE));
        tooltip.add(Text.translatable("item.craq.thick_fluid_bucket.tooltip2").formatted(Formatting.DARK_PURPLE));
        tooltip.add(Text.translatable("item.craq.thick_fluid_bucket.tooltip3").formatted(Formatting.DARK_PURPLE));
    }
}
