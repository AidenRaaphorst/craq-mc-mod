package net.craqbois.craq.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

public class StonerData {
    private static final String STONER_VALUE_NBT_STRING = "stonerValue";
    private static final String IS_STONER_NBT_STRING = "isStoner";

    public static boolean isStoner(IEntityDataSaver player) {
        return player.getPersistentData().getBoolean(IS_STONER_NBT_STRING);
    }

    public static void setStoner(IEntityDataSaver player, boolean bool) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putBoolean(IS_STONER_NBT_STRING, bool);

        // TODO: Sync data
        // TODO: Show UI meter

        // Send debug message
        ((PlayerEntity) player).sendMessage(Text.literal("Is stoner: " + player.getPersistentData().getBoolean(IS_STONER_NBT_STRING)));
    }

    public static void addToWeedMeter(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int stonerValue = nbt.getInt(STONER_VALUE_NBT_STRING);

        amount = Math.min(stonerValue + amount, 10);
        nbt.putInt(STONER_VALUE_NBT_STRING, amount);
        // TODO: Sync data

        // Send debug message
        ((PlayerEntity) player).sendMessage(Text.literal("Weed meter value: " + player.getPersistentData().getInt(STONER_VALUE_NBT_STRING)));
    }

    public static void removeFromWeedMeter(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int stonerValue = nbt.getInt(STONER_VALUE_NBT_STRING);

        amount = Math.max(stonerValue + amount, 0);
        nbt.putInt(STONER_VALUE_NBT_STRING, amount);
        // TODO: Sync data

        // Send debug message
        ((PlayerEntity) player).sendMessage(Text.literal("Weed meter value: " + player.getPersistentData().getInt(STONER_VALUE_NBT_STRING)));
    }
}
