package me.minimize.skyblockenchants.config;

import me.minimize.skyblockenchants.SkyblockEnchants;

/**
 * Simple wrapper for config operations.
 * Author: MrMiniMize & minimize0x
 */
public final class ConfigManager {

    private final SkyblockEnchants plugin;

    public ConfigManager(SkyblockEnchants plugin) {
        this.plugin = plugin;
    }

    public void reload() {
        plugin.reloadConfig();
    }

    public double getBuyRandomScrollCost() {
        return plugin.getConfig().getDouble("buy-random-scroll-cost", 1000.0);
    }

    public double getSlotMachineCost() {
        return plugin.getConfig().getDouble("slot-machine-cost", 500.0);
    }

    // Additional getters can be added as needed
}
