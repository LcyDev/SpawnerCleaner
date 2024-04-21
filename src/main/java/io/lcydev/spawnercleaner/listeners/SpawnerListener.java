package io.lcydev.spawnercleaner.listeners;

import io.lcydev.spawnercleaner.SpawnerCleaner;
import io.lcydev.spawnercleaner.utils.Config;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

public class SpawnerListener implements Listener {

    private final SpawnerCleaner instance;
    private final Config config;

    public SpawnerListener() {
        instance = SpawnerCleaner.inst();
        config = Config.get();
    }

    @EventHandler
    public void onSpawnerEvent(SpawnerSpawnEvent e) {
        if (!config.getWorlds().contains(e.getLocation().getWorld().getName())) return;

        Location spawnerLoc = e.getSpawner().getLocation();
        instance.removeQueue.add(spawnerLoc);
        e.setCancelled(true);
    }
}
