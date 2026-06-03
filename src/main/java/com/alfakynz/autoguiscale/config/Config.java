package com.alfakynz.autoguiscale.config;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.alfakynz.autoguiscale.AutoGuiScale.LOGGER;

public class Config {

    private static final Path CONFIG_PATH = Path.of("config", "auto-gui-scale.txt");

    public static boolean ENABLED = true;
    public static int REDUCED = 1;

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

                if (line.startsWith("enabled=")) {
                    String value = line.substring("enabled=".length()).toLowerCase();
                    ENABLED = !value.equals("false");
                }
                else if (line.startsWith("reduced=")) {
                    String value = line.substring("reduced=".length()).toLowerCase();
                    REDUCED = Integer.parseInt(value);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load Auto GUI Scale configuration.", e);
        }
    }

    public static void save() {
        try (Writer writer = new FileWriter(CONFIG_PATH.toString())) {
            String enabledString = Boolean.toString(ENABLED);
            String reducedString = Integer.toString(REDUCED);
            writer.write("enabled=" + enabledString + "\n");
            writer.write("reduced=" + reducedString + "\n");
        } catch (IOException e) {
            LOGGER.error("Failed to save Auto GUI Scale configuration.", e);
        }
    }

    private static void createDefaultFile() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH,
                    """
                         enabled=true
                         reduced=1
                         """);
        } catch (IOException e) {
            LOGGER.error("Failed to create Auto GUI Scale configuration file.", e);
        }
    }
}