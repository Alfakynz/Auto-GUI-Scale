package com.alfakynz.autoguiscale;


import com.alfakynz.autoguiscale.config.Config;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoGuiScale implements ModInitializer {
	public static final String MOD_NAME = "Auto GUI Scale";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		Config.load();
	}
}