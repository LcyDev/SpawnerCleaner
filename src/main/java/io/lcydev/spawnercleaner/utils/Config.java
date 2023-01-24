package io.lcydev.spawnercleaner.utils;

import io.lcydev.spawnercleaner.SpawnerCleaner;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * The class for loading and storing the configuration options.
 */
@Getter
public class Config {

    // The static instance of the plugin.s
    private static SpawnerCleaner instance;
    // The static instance of the config.
    private static Config config;
    // The list of config filenames.
    private static final String[] filenames = { "config.yml" };
    // The list of files.
    private static File[] files;
    // The list of configs.
    private static YamlConfiguration[] configs;

    private final String logLevel;
    private final String mode;
    private final List<String> worlds;

    /**
     * Initializes the config files.
     */
    public static boolean init() {
        try {
            instance = SpawnerCleaner.inst();
            initializeConfig();
            return true;
        } catch (Exception e) {
            instance.getLogger().warning("Failed to initialize config.");
            e.printStackTrace();
        }
        return false;
    }

    private static void initializeConfig() {
        files = new File[filenames.length];
        configs = new YamlConfiguration[filenames.length];
        config = new Config();
    }

    /**
     * Gets the config.
     *
     * @return the config object
     */
    public static Config get() {
        return config;
    }

    private Config() {

        for (String filename : filenames) {
            if (!new File(instance.getDataFolder(), filename).exists()) {
                instance.saveResource(filename, false);
            }
        }

        for (int i = 0; i < filenames.length; i++) {
            File file = new File(instance.getDataFolder(), filenames[i]);
            if (!file.exists()) {
                instance.getLogger().info("&cFailed to load config file: " + filenames[i]);
                continue;
            }
            files[i] = file;
            configs[i] = YamlConfiguration.loadConfiguration(files[i]);
        }

        this.logLevel = configs[0].getString("log-level", "INFO");
        myLogger.loadLogger(Level.parse(logLevel));
        myLogger logger = myLogger.getLog();
        this.worlds = configs[0].getStringList("worlds");
        logger.fine("Enabled worlds: " + Arrays.toString(worlds.toArray()));
        this.mode = configs[0].getString("mode", "EVENT");
        logger.fine("Mode: " + mode);
    }
}
