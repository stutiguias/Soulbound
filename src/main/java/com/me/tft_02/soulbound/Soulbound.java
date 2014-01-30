package com.me.tft_02.soulbound;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.me.tft_02.soulbound.commands.Commands;
import com.me.tft_02.soulbound.config.Config;
import com.me.tft_02.soulbound.config.ItemsConfig;
import com.me.tft_02.soulbound.listeners.BlockListener;
import com.me.tft_02.soulbound.listeners.EntityListener;
import com.me.tft_02.soulbound.listeners.InventoryListener;
import com.me.tft_02.soulbound.listeners.PlayerListener;
import com.me.tft_02.soulbound.util.LogFilter;

import org.mcstats.Metrics;

public class Soulbound extends JavaPlugin {
    /* File Paths */
    private static String mainDirectory;

    public static Soulbound p;

    // Jar Stuff
    public static File soulbound;

    // Update Check
    private boolean updateAvailable;

    /**
     * Run things on enable.
     */
    @Override
    public void onEnable() {
        p = this;
        getLogger().setFilter(new LogFilter(this));

        setupFilePaths();

        loadConfigFiles();

        registerEvents();

        getCommand("soulbound").setExecutor(new Commands(this));
        getCommand("bind").setExecutor(new Commands(this));
        getCommand("bindonpickup").setExecutor(new Commands(this));
        getCommand("bindonuse").setExecutor(new Commands(this));
        getCommand("bindonequip").setExecutor(new Commands(this));
        getCommand("unbind").setExecutor(new Commands(this));

        if (Config.getInstance().getStatsTrackingEnabled()) {
            try {
                Metrics metrics = new Metrics(this);
                metrics.start();
            }
            catch (IOException e) {
            }
        }
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new InventoryListener(this), this);
        pm.registerEvents(new EntityListener(this), this);
        pm.registerEvents(new BlockListener(this), this);
    }

    /**
     * Run things on disable.
     */
    @Override
    public void onDisable() {}

    public static String getMainDirectory() {
        return mainDirectory;
    }

    public boolean isUpdateAvailable() {
        return updateAvailable;
    }

    public void debug(String message) {
        getLogger().info("[Debug] " + message);
    }

    /**
     * Setup the various storage file paths
     */
    private void setupFilePaths() {
        soulbound = getFile();
        mainDirectory = getDataFolder().getPath() + File.separator;
    }

    private void loadConfigFiles() {
        Config.getInstance();
        ItemsConfig.getInstance();
    }
    
}
