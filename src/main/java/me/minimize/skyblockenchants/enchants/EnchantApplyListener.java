package me.minimize.skyblockenchants.enchants;

import me.minimize.skyblockenchants.SkyblockEnchants;
import me.minimize.skyblockenchants.utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Minimal example for applying enchant effects.
 * Author: MrMiniMize & minimize0x
 */
public final class EnchantApplyListener implements Listener {

    private final SkyblockEnchants plugin;

    public EnchantApplyListener(SkyblockEnchants plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getType() == Material.AIR) return;

        // Example: if lore contains "LIGHTNING STRIKER", strike lightning
        if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasLore()) {
            for (String line : event.getItem().getItemMeta().getLore()) {
                String strip = ChatColor.stripColor(line).toUpperCase();
                if (strip.contains("LIGHTNING STRIKER")) {
                    event.getPlayer().getWorld().strikeLightning(event.getPlayer().getLocation());
                    ChatUtil.send(event.getPlayer(), "&eLightning Striker triggered!");
                    break;
                }
                // Here you can add more if/else checks for "HEALING AURA", "EXP BOOST", etc.
            }
        }
    }
}
