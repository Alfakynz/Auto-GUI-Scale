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
                .setSavingRunnable(() -> {
                    Config.save();
                    Minecraft.getInstance().resizeGui();
                });

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        var generalCategory = builder.getOrCreateCategory(Component.translatable("option.auto_gui_scale.config.general"));

        generalCategory.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable(Config.ENABLED.text),
                        Config.ENABLED.value
                )
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Config.ENABLED.value = newValue)
                .build()
        );

        generalCategory.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable(Config.DEBUG.text),
                        Config.DEBUG.value
                )
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> Config.DEBUG.value = newValue)
                .build()
        );

        generalCategory.addEntry(entryBuilder
                .startIntSlider(
                        Component.translatable(Config.DIVIDED.text),
                        (int) Math.round(Config.DIVIDED.value * 10),
                        10,
                        50
                )
                .setDefaultValue(15)
                .setTextGetter(value -> Component.literal(String.format("Value: %.1f", value / 10.0)))
                .setSaveConsumer(newValue -> Config.DIVIDED.value = newValue / 10.0)
                .build()
        );

        generalCategory.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable(Config.ROUND.text),
                        Config.ROUND.value
                )
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Config.ROUND.value = newValue)
                .build()
        );

        generalCategory.addEntry(entryBuilder
                .startIntSlider(
                        Component.translatable(Config.REDUCED.text),
                        Config.REDUCED.value,
                        0,
                        5
                )
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> Config.REDUCED.value = newValue)
                .build()
        );

        generalCategory.addEntry(entryBuilder
                .startIntSlider(
                        Component.translatable(Config.MINIMUM.text),
                        Config.MINIMUM.value,
                        1,
                        6
                )
                .setDefaultValue(2)
                .setSaveConsumer(newValue -> Config.MINIMUM.value = newValue)
                .build()
        );

        return builder.build();
    }
}