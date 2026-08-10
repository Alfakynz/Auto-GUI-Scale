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
    public static boolean DEBUG = false;
    public static double DIVIDED = 1.5;
    public static int REDUCED = 1;
    public static int MINIMUM = 2;

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
                    ENABLED = value.equals("true");
                }
                else if (line.startsWith("debug=")) {
                    String value = line.substring("debug=".length()).toLowerCase();
                    DEBUG = value.equals("true");
                }
                else if (line.startsWith("divided=")) {
                    String value = line.substring("divided=".length()).toLowerCase();
                    DIVIDED = Double.parseDouble(value);
                }
                else if (line.startsWith("reduced=")) {
                    String value = line.substring("reduced=".length()).toLowerCase();
                    REDUCED = Integer.parseInt(value);
                }
                else if (line.startsWith("minimum=")) {
                    String value = line.substring("minimum=".length()).toLowerCase();
                    MINIMUM = Integer.parseInt(value);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load Auto GUI Scale configuration.", e);
        }
    }

    public static void save() {
        try (Writer writer = new FileWriter(CONFIG_PATH.toString())) {
            String enabledString = Boolean.toString(ENABLED);
            String debugString = Boolean.toString(DEBUG);
            String dividedString = Double.toString(DIVIDED);
            String reducedString = Integer.toString(REDUCED);
            String minimumString = Integer.toString(MINIMUM);
            writer.write("enabled=" + enabledString + "\n");
            writer.write("debug=" + debugString + "\n");
            writer.write("divided=" + dividedString + "\n");
            writer.write("reduced=" + reducedString + "\n");
            writer.write("minimum=" + minimumString + "\n");
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
                         debug=false
                         divided=1.5
                         reduced=0
                         minimum=2
                         """);
        } catch (IOException e) {
            LOGGER.error("Failed to create Auto GUI Scale configuration file.", e);
        }
    }
}