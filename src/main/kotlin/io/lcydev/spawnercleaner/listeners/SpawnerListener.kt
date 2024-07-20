package io.lcydev.template.listeners

import io.lcydev.template.SpawnerCleaner
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener
import org.bukkit.event.entity.SpawnerSpawnEvent

class SpawnerListener(private val plugin: SpawnerCleaner) : Listener {

    @EventHandler
    fun onSpawnerEvent(e: SpawnerSpawnEvent) {

        val spawner = e.spawner
        val location = spawner?.location
        val world = location?.world

        if (false) return

        if (spawner != null) {
            plugin.removeQueue.add(location)
        }
        e.isCancelled = true
    }
}