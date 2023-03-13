package net.craqbois.craq.item;

import net.craqbois.craq.util.IEntityDataSaver;
import net.craqbois.craq.util.StonerData;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class JonkoItem extends Item {
    private static final int DEFAULT_USE_TIME = 100;
    private static final int STONER_USE_TIME = 20;
    private int useTime = DEFAULT_USE_TIME;

    public JonkoItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);

        if (user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (!((PlayerEntity)user).getAbilities().creativeMode) {
            stack.decrement(1);
        }

        if (!world.isClient) {
            if(!StonerData.isStoner((IEntityDataSaver) user)) {
                StonerData.setStoner((IEntityDataSaver) user, true);
            }
            StonerData.addToWeedMeter((IEntityDataSaver) user, 2);

            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 10 * 20));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 30 * 20));
        }

        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return useTime;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        // TODO: Add smoking sound
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        useTime = StonerData.isStoner((IEntityDataSaver) user) ? STONER_USE_TIME : DEFAULT_USE_TIME;
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
