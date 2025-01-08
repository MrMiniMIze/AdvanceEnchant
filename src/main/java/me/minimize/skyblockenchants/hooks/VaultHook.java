package me.minimize.skyblockenchants.hooks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import net.milkbowl.vault.economy.Economy;
import me.minimize.skyblockenchants.utils.ChatUtil;

/**
 * Manages hooking into Vault's Economy.
 * Author: MrMiniMize & minimize0x
 */
public final class VaultHook {

    private static Economy economy = null;

    public boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp =
                Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        economy = rsp.getProvider();
        ChatUtil.log("&a[SkyblockEnchants] Vault economy hooked: " + economy.getName());
        return (economy != null);
    }

    public static Economy getEconomy() {
        return economy;
    }
}
