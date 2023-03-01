package net.craqbois.craq;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CraqMod implements ModInitializer {
	public static final String modId = "craq";
	public static final Logger LOGGER = LoggerFactory.getLogger(modId);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}
