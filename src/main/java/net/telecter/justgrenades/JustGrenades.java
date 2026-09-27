package net.telecter.justgrenades;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.telecter.justgrenades.entity.ModEntityType;
import net.telecter.justgrenades.items.ModItems;

public class JustGrenades implements ModInitializer {
	public static final String MOD_ID = "justgrenades";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModEntityType.initialize();
	}
}