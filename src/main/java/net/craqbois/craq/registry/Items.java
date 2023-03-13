package net.craqbois.craq.registry;

import net.craqbois.craq.CraqMod;
import net.craqbois.craq.item.JonkoItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class Items {

    // Items here
    public static final JonkoItem JONKO = new JonkoItem(new FabricItemSettings().maxCount(8));

    public static void registerItems() {
        CraqMod.LOGGER.info("Registering items for " + CraqMod.MOD_ID);

        // Register items
        registerItem("jonko", JONKO);

        // Add items to groups
        addItemToGroup(JONKO, ItemGroups.FOOD_AND_DRINK);
    }

    private static void registerItem(String name, Item item) {
        Registry.register(Registries.ITEM, new Identifier(CraqMod.MOD_ID, name), item);
    }

    private static void addItemToGroup(Item item, ItemGroup itemGroup) {
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> content.add(item));
    }
}
