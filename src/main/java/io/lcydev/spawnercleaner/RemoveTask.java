package io.lcydev.spawnercleaner;

import io.lcydev.spawnercleaner.utils.myLogger;
import org.bukkit.Location;
import org.bukkit.Material;

public class RemoveTask implements Runnable {
    private static SpawnerCleaner instance;
    private static myLogger logger;

    public RemoveTask() {
        instance = SpawnerCleaner.inst();
        logger = myLogger.getLog();
    }

    @Override
    public void run() {
        Location spawnerLoc = instance.removeQueue.poll();
        if (spawnerLoc != null) {
            spawnerLoc.getBlock().setType(Material.AIR);
            logger.fine(String.format("Removed spawner at %s: %.1f %.1f %.1f", spawnerLoc.getWorld(), spawnerLoc.getX(), spawnerLoc.getY(), spawnerLoc.getZ()));
        }
    }
}
