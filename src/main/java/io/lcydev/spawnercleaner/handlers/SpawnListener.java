package io.lcydev.spawnercleaner.handlers;

import io.lcydev.spawnercleaner.SpawnerCleaner;
import io.lcydev.spawnercleaner.utils.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class SpawnListener implements Listener {

    private final SpawnerCleaner instance;
    private final Config config;

    public SpawnListener() {
        instance = SpawnerCleaner.inst();
        config = Config.get();
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER) return;
        if (!config.getWorlds().contains(e.getLocation().getWorld().getName())) return;

        Location location = e.getLocation();
        findSpawner(location);
        e.setCancelled(true);
    }

    private void findSpawner(Location spawnLoc) {
        World world = spawnLoc.getWorld();
        SCAN: for (int x = spawnLoc.getBlockX() - 8; x <= spawnLoc.getBlockX() + 8; x++) {
            for (int z = spawnLoc.getBlockZ() - 8; z <= spawnLoc.getBlockZ() + 8; z++) {
                for (int y = spawnLoc.getBlockY() - 3; y <= spawnLoc.getBlockY() + 3; y++) {
                    if (!world.getBlockAt(x, y, z).getType().equals(Material.SPAWNER)) continue;
                    instance.removeQueue.add(new Location(world, x, y, z));
                    break SCAN;
                }
            }
        }
    }
}
