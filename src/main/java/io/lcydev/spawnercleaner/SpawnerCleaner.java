package io.lcydev.spawnercleaner;

import io.lcydev.spawnercleaner.utils.Config;
import io.lcydev.spawnercleaner.listeners.SpawnerListener;
import io.lcydev.spawnercleaner.listeners.PreSpawnerListener;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.LinkedList;
import java.util.Queue;

public final class SpawnerCleaner extends JavaPlugin {

    //Instance Object to use when no Object was passed!
    private static SpawnerCleaner instance;
    // Queue blocks for removal in a modifiable List
    public Queue<Location> removeQueue = new LinkedList<>();
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
            getLogger().info("SpawnerCleaner is now Enabled!");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("SpawnerCleaner is now Disabled!");
    }

    private void Initialize() {
        Config config = Config.get();
        BukkitScheduler scheduler = Bukkit.getScheduler();
        PluginManager pluginManager = Bukkit.getPluginManager();

        if (config.getMode().equalsIgnoreCase("EVENT")) {
            if (isPaper) {
                pluginManager.registerEvents(new PreSpawnerListener(), this);
            } else {
                pluginManager.registerEvents(new SpawnerListener(), this);
            }
        } else if (config.getMode().equalsIgnoreCase("CHUNK")) {
        }
        scheduler.scheduleSyncRepeatingTask(this, new RemoveTask(), 0L, 8L);
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
