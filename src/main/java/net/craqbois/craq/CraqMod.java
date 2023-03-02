package net.craqbois.craq;

import net.craqbois.craq.registry.Items;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CraqMod implements ModInitializer {
	public static final String MOD_ID = "craq";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		Items.register();
	}
}
