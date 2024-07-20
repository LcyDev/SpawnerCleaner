package io.lcydev.template;

import gg.flyte.twilight.twilight
import io.lcydev.template.command.PingCommand
import io.lcydev.template.handlers.ChunkManager
import io.lcydev.template.listeners.PreSpawnerListener
import io.lcydev.template.listeners.SpawnerListener
import org.bukkit.Location
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler
import java.util.*
import kotlin.system.measureTimeMillis


class SpawnerCleaner : JavaPlugin() {

    companion object {
        const val CONFIG_VERSION = "1.1.0"

        lateinit var instance: SpawnerCleaner
            private set
    }

    private val pluginManager: PluginManager = server.pluginManager
    val chunkManager: ChunkManager = ChunkManager()

    // Queue blocks for removal in a modifiable List
    var removeQueue: Queue<Location> = LinkedList()

    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
        val timeTaken = measureTimeMillis {
            registerListeners()
        }
        logger.info("Plugin enabled in time $timeTaken ms")

        twilight(this) { }

        BukkitCommandHandler.create(this).apply {
            enableAdventure()
            register(PingCommand())
            registerBrigadier()
        }
    }

    private fun registerListeners() {
        val listeners = arrayListOf(
            SpawnerListener(this),
            PreSpawnerListener(this),
        )
        val timeTaken = measureTimeMillis {
            listeners.forEach { listener ->
                pluginManager.registerEvents(listener, this)
                logger.info("Bukkit Listener ${listener::class.simpleName} registered () -> ok")
            }
        }
        logger.info("Listeners registered (${listeners.size}) in time $timeTaken ms -> ok")
    }
}