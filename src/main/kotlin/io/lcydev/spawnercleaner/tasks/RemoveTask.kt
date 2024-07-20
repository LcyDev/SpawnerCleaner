package tasks

import io.lcydev.template.SpawnerCleaner
import org.bukkit.Location
import org.bukkit.Material

class RemoveTask(private val plugin: SpawnerCleaner) : Runnable {

    override fun run() {
        val location: Location = plugin.removeQueue.poll() ?: return

        location.block.type = Material.AIR
        plugin.slF4JLogger.info("Removed ${location.block.type} in world ${location.world} at ${location.blockX}, ${location.blockY}, ${location.blockZ}")
    }
}