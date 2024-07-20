package io.lcydev.template.listeners

import io.lcydev.template.SpawnerCleaner
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.ChunkLoadEvent

class ChunkLoadListener(private val plugin: SpawnerCleaner) : Listener {

    @EventHandler
    fun onChunkLoad(e: ChunkLoadEvent) {
        plugin.chunkManager.initChunk()
    }
}