package net.craqbois.craq.registry;

import net.craqbois.craq.CraqMod;
import net.craqbois.craq.item.AidenItem;
import net.craqbois.craq.item.ThickFluidBucketItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class Items {

    // Items here
    public static final AidenItem AIDEN_ITEM = new AidenItem(new FabricItemSettings());
	public static final ThickFluidBucketItem FLUID_BUCKET_ITEM = new ThickFluidBucketItem(new FabricItemSettings().maxCount(1));

    public static void register() {
		CraqMod.LOGGER.info("Registerring items for " + CraqMod.MOD_ID);

        // Register items
		Registry.register(Registries.ITEM, new Identifier(CraqMod.MOD_ID, "aiden"), AIDEN_ITEM);
		Registry.register(Registries.ITEM, new Identifier(CraqMod.MOD_ID, "thick_fluid_bucket"), FLUID_BUCKET_ITEM);

		// Add items to groups
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.add(AIDEN_ITEM));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> content.add(FLUID_BUCKET_ITEM));
    }
}
