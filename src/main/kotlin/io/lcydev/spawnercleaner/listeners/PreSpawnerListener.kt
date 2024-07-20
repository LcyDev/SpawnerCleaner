package io.lcydev.template.listeners


import com.destroystokyo.paper.event.entity.PreSpawnerSpawnEvent;
import io.lcydev.template.SpawnerCleaner
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PreSpawnerListener(private val plugin: SpawnerCleaner) : Listener {

    @EventHandler
    fun onPreSpawnerEvent(e: PreSpawnerSpawnEvent) {

        val location = e.spawnerLocation
        val world = location.world

        if (false) return

        plugin.removeQueue.add(location)

        e.isCancelled = true
    }
}