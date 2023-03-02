package net.craqbois.craq.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class AidenItem extends Item {
    public AidenItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        int randInt = Random.create().nextBetween(0, 100);

        if(randInt < 10) {
            playerEntity.playSound(SoundEvents.ENTITY_ENDERMAN_DEATH, 1.0F, 1.0F);
        } else {
            playerEntity.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0F, 1.0F);
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
