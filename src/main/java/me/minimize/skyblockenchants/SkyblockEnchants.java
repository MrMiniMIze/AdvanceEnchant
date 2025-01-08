package me.minimize.skyblockenchants;

import org.bukkit.plugin.java.JavaPlugin;

import me.minimize.skyblockenchants.commands.EnchAdminCommand;
import me.minimize.skyblockenchants.commands.EnchMainCommand;
import me.minimize.skyblockenchants.config.ConfigManager;
import me.minimize.skyblockenchants.enchants.EnchantApplyListener;
import me.minimize.skyblockenchants.enchants.EnchantManager;
import me.minimize.skyblockenchants.gui.GUIInteractListener;
import me.minimize.skyblockenchants.hooks.VaultHook;
import me.minimize.skyblockenchants.scrolls.ScrollManager;
import me.minimize.skyblockenchants.utils.ChatUtil;

/**
 * Main plugin class for SkyblockEnchants.
 * Author: MrMiniMize & minimize0x
 */
public final class SkyblockEnchants extends JavaPlugin {

    private static SkyblockEnchants instance;

    private ConfigManager configManager;
    private VaultHook vaultHook;
    private EnchantManager enchantManager;
    private ScrollManager scrollManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig(); // Create config if missing
        configManager = new ConfigManager(this);

        // Setup Vault
        vaultHook = new VaultHook();
        if (!vaultHook.setupEconomy()) {
            ChatUtil.log("&c[SkyblockEnchants] Vault not found or no economy plugin. Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Enchant + Scroll managers
        enchantManager = new EnchantManager(this);
        scrollManager = new ScrollManager(this);

        // Register event listeners
        getServer().getPluginManager().registerEvents(new GUIInteractListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantApplyListener(this), this);

        // Register commands from config alias
        String mainCmdAlias = getConfig().getString("command-alias", "ench").toLowerCase();
        getCommand(mainCmdAlias).setExecutor(new EnchMainCommand(this));
        getCommand("enchadmin").setExecutor(new EnchAdminCommand(this));

        ChatUtil.log("&aSkyblockEnchants enabled successfully!");
    }

    @Override
    public void onDisable() {
        ChatUtil.log("&cSkyblockEnchants disabled.");
    }

    public static SkyblockEnchants getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public VaultHook getVaultHook() {
        return vaultHook;
    }

    public EnchantManager getEnchantManager() {
        return enchantManager;
    }

    public ScrollManager getScrollManager() {
        return scrollManager;
    }
}
