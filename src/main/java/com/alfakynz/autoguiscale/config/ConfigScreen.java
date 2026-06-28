package com.alfakynz.autoguiscale.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

public class ConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("option.auto_gui_scale.config"))
                .setSavingRunnable(Config::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        var generalCategory = builder.getOrCreateCategory(Component.translatable("option.auto_gui_scale.config.general"));

        generalCategory.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable("option.auto_gui_scale.config.enable"),
                        Config.ENABLED
                )
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> {
                    Config.ENABLED = newValue;
                    Minecraft.getInstance().resizeDisplay();
                })
                .build()
        );

        generalCategory.addEntry(entryBuilder
                .startIntSlider(
                        Component.translatable("option.auto_gui_scale.config.reduce"),
                        Config.REDUCED,
                        0,
                        3
                )
                .setDefaultValue(1)
                .setSaveConsumer(newValue -> {
                    Config.REDUCED = newValue;
                    Minecraft.getInstance().resizeDisplay();
                })
                .build()
        );

        generalCategory.addEntry(entryBuilder
                .startIntSlider(
                        Component.translatable("option.auto_gui_scale.config.minimum"),
                        Config.MINIMUM,
                        0,
                        4
                )
                .setDefaultValue(2)
                .setSaveConsumer(newValue -> {
                    Config.MINIMUM = newValue;
                    Minecraft.getInstance().resizeDisplay();
                })
                .build()
        );

        return builder.build();
    }
}