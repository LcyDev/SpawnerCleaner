package io.lcydev.spawnercleaner;

import io.lcydev.spawnercleaner.utils.Config;
import io.papermc.lib.PaperLib;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnerCleaner extends JavaPlugin {

    //Instance Object to use when no Object was passed!
    private static SpawnerCleaner instance;
    // Does the server implement Paper
    private boolean isPaper;

    public SpawnerCleaner() {
        super();
        instance = this;
    }

    @Override
    public void onEnable() {
        isPaper = PaperLib.isPaper();
        PaperLib.suggestPaper(this);
        if (!Config.init()) {
            instance.getPluginLoader().disablePlugin(this);
        } else {
            Initialize();
            getLogger().info("Spawner-Cleanser is now Enabled!");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Spawner-Cleanser is now Disabled!");
    }

    private void Initialize() {
    }

    /**
     * Gets the instance of the SpawnerCleaner plugin.
     *
     * @return The instance of this plugin.
     */
    public static SpawnerCleaner inst() {
        return instance;
    }
}
