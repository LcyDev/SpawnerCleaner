package io.lcydev.spawnercleaner.listeners;

import io.lcydev.spawnercleaner.SpawnerCleaner;
import io.lcydev.spawnercleaner.handlers.ChunkManager;
import io.lcydev.spawnercleaner.utils.Config;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoadListener implements Listener {

    public ChunkLoadListener() {
        instance = SpawnerCleaner.inst();
        config = Config.get();
    }

    public void onChunkLoad(ChunkLoadEvent e) {
        ChunkManager.initChunk(e.getChunk());
    }

}
