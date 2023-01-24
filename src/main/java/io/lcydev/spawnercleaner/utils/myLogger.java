package io.lcydev.spawnercleaner.utils;

import io.lcydev.spawnercleaner.SpawnerCleaner;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * The custom Auto-Tune IconChangerLogger class.
 */
@Getter
@Setter
public class myLogger {

    private Logger logger;
    private Level level;
    @Getter
    private static myLogger log;

    /**
     * Loads the logger.
     */
    public static void loadLogger(@NotNull Level level) {
        log = new myLogger(SpawnerCleaner.inst());
        log.setLevel(level);
        log.info("Logger loaded with level " + log.getLevel().toString());
    }

    public myLogger(@NotNull JavaPlugin plugin) {
        this.logger = plugin.getLogger();
        this.level = Level.INFO;
    }

    private static String prefix(@NotNull Level level, @NotNull String msg) {
        return  "[" + level.getName() + "] " +  ChatColor.translateAlternateColorCodes('&', msg);
    }

    private boolean shouldLog(@NotNull Level level) {
        return level.intValue() >= this.level.intValue();
    }

    /**
     * Logs a message at the given level with an optional prefix.
     *
     * @param level   the level to log at
     * @param message the message to log
     * @param param1  the parameters to use in the message
     */
    public void log(@NotNull Level level, @NotNull String message, @NotNull Object param1) {
        if (shouldLog(level)) {
            logger.log(Level.INFO, prefix(level, message), param1);
        }
    }

    /**
     * Logs a message at the given level with optional prefixes.
     *
     * @param level   the level to log at
     * @param message the message to log
     * @param params  the parameters to log
     */
    public void log(@NotNull Level level, @NotNull String message, Object... params) {
        if (shouldLog(level)) {
            logger.log(Level.INFO, prefix(level, message), params);
        }
    }

    /**
     * Logs a message at the given level.
     */
    public void log(@NotNull Level level, @NotNull String message) {
        if (shouldLog(level)) {
            logger.log(Level.INFO, prefix(level, message));
        }
    }

    public void fine(@NotNull String message) {
        log(Level.FINE, message);
    }

    public void finer(@NotNull String message) {
        log(Level.FINER, message);
    }

    public void finest(@NotNull String message) {
        log(Level.FINEST, message);
    }

    public void config(@NotNull String message) {
        log(Level.CONFIG, message);
    }

    public void info(@NotNull String message) {
        log(Level.INFO, message);
    }

    public void warning(@NotNull String message) {
        log(Level.WARNING, message);
    }

    public void severe(@NotNull String message) {
        log(Level.SEVERE, message);
    }

}