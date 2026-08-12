package com.alfakynz.autoguiscale.config;

import com.alfakynz.autoguiscale.config.config_items.ConfigItem;
import com.alfakynz.autoguiscale.config.config_items.ConfigItemBool;
import com.alfakynz.autoguiscale.config.config_items.ConfigItemDouble;
import com.alfakynz.autoguiscale.config.config_items.ConfigItemInt;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.alfakynz.autoguiscale.AutoGuiScale.LOGGER;

public class Config {

    private static final Path CONFIG_PATH = Path.of("config", "auto-gui-scale.txt");

    public static ConfigItem<Boolean> ENABLED = new ConfigItemBool("enabled","option.auto_gui_scale.config.enable", true);
    public static ConfigItem<Boolean> DEBUG = new ConfigItemBool("debug", "option.auto_gui_scale.config.debug", false);
    public static ConfigItem<Double> DIVIDED = new ConfigItemDouble("divided", "option.auto_gui_scale.config.divide", 1.5, 1.0, 5.0);
    public static ConfigItem<Integer> REDUCED = new ConfigItemInt("reduced", "option.auto_gui_scale.config.reduce", 0, 0, 5);
    public static ConfigItem<Integer> MINIMUM = new ConfigItemInt("minimum", "option.auto_gui_scale.config.minimum", 2, 1, 6);
    public static List<ConfigItem<?>> configItems = List.of(ENABLED, DEBUG, DIVIDED, REDUCED, MINIMUM);

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            createDefaultFile();
            return;
        }

        try {
            List<String> lines = Files.readAllLines(CONFIG_PATH);
            for (String line : lines) {
                line = line.trim();

                if (line.startsWith("#") || line.isEmpty()) continue;

                for (ConfigItem<?> item : configItems) {
                    if (line.startsWith(item.getName())) {
                        String value = line.substring(item.getName().length()).toLowerCase();
                        item.setValue(value);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load Auto GUI Scale configuration.", e);
        }
    }

    public static void save() {
        try (Writer writer = new FileWriter(CONFIG_PATH.toString())) {
            for (ConfigItem<?> item : configItems) {
                writer.write(item.getConfigText());
            }
        } catch (IOException e) {
            LOGGER.error("Failed to save Auto GUI Scale configuration.", e);
        }
    }

    private static void createDefaultFile() {
        try {
            StringBuilder defaultText = new StringBuilder();

            for (ConfigItem<?> item : configItems) {
                defaultText.append(item.getConfigDefaultText());
            }
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, defaultText);
        } catch (IOException e) {
            LOGGER.error("Failed to create Auto GUI Scale configuration file.", e);
        }
    }
}