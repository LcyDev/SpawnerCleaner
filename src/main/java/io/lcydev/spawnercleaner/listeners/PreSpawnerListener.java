package io.lcydev.spawnercleaner.listeners;

import com.destroystokyo.paper.event.entity.PreSpawnerSpawnEvent;
import io.lcydev.spawnercleaner.SpawnerCleaner;
import io.lcydev.spawnercleaner.utils.Config;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PreSpawnerListener implements Listener {

    private final SpawnerCleaner instance;
    private final Config config;

    public PreSpawnerListener() {
        instance = SpawnerCleaner.inst();
        config = Config.get();
    }

    @EventHandler
    public void onPreSpawnerEvent(PreSpawnerSpawnEvent e) {
        if (!config.getWorlds().contains(e.getSpawnerLocation().getWorld().getName())) return;

        Location spawnerLoc = e.getSpawnerLocation();
        instance.removeQueue.add(spawnerLoc);
        e.setCancelled(true);
    }
}
