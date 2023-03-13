package net.craqbois.craq.mixin;

import net.craqbois.craq.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityDataSaverMixin implements IEntityDataSaver {
    private static final String STONER_DATA_NBT_STRING = "stonerData";
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if(this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(at = @At("HEAD"), method = "writeNbt")
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
        if(persistentData != null) {
            nbt.put(STONER_DATA_NBT_STRING, persistentData);
        }
    }

    @Inject(at = @At("HEAD"), method = "readNbt")
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if(nbt.contains(STONER_DATA_NBT_STRING, 10)) {
            persistentData = nbt.getCompound(STONER_DATA_NBT_STRING);
        }
    }
}
