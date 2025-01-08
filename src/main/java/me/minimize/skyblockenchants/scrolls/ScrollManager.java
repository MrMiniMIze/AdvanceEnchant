package me.minimize.skyblockenchants.scrolls;

import me.minimize.skyblockenchants.SkyblockEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 * Manages creating, identifying, randomizing scroll items.
 * Author: MrMiniMize & minimize0x
 */
public final class ScrollManager {

    private final SkyblockEnchants plugin;
    private final Random random = new Random();

    public ScrollManager(SkyblockEnchants plugin) {
        this.plugin = plugin;
    }

    public ItemStack createScroll(String tier) {
        ItemStack scroll = new ItemStack(Material.PAPER);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                "&bEnchant Scroll (" + tier + ")"));
        meta.setLore(Arrays.asList(
                ChatColor.GRAY + "Tier: " + tier,
                ChatColor.DARK_GRAY + "ScrollID: " + UUID.randomUUID()
        ));
        scroll.setItemMeta(meta);
        return scroll;
    }

    public boolean isScroll(ItemStack item) {
        if (item == null) return false;
        if (!item.hasItemMeta()) return false;
        if (!item.getItemMeta().hasLore()) return false;

        for (String line : item.getItemMeta().getLore()) {
            String strip = ChatColor.stripColor(line);
            if (strip.startsWith("Tier: ")) {
                return true;
            }
        }
        return false;
    }

    public String getScrollTier(ItemStack item) {
        if (!isScroll(item)) return "COMMON";
        for (String line : item.getItemMeta().getLore()) {
            String strip = ChatColor.stripColor(line);
            if (strip.startsWith("Tier: ")) {
                return strip.substring("Tier: ".length()).toUpperCase();
            }
        }
        return "COMMON";
    }

    public String getRandomTier(String configSectionKey) {
        // e.g. "buy-menu-tiers" or "slot-machine-tiers"
        ConfigurationSection sec = plugin.getConfig().getConfigurationSection(configSectionKey);
        if (sec == null) {
            return "COMMON";
        }

        double total = 0;
        for (String tier : sec.getKeys(false)) {
            total += sec.getDouble(tier, 0);
        }

        double r = random.nextDouble() * total;
        double cumulative = 0;
        for (String tier : sec.getKeys(false)) {
            double w = sec.getDouble(tier, 0);
            cumulative += w;
            if (r <= cumulative) {
                return tier.toUpperCase();
            }
        }
        return "COMMON";
    }
}
