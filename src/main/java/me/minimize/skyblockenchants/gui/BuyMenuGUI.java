package me.minimize.skyblockenchants.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Buy random scroll menu
 * Author: MrMiniMize & minimize0x
 */
public final class BuyMenuGUI {

    private static final String TITLE = ChatColor.BLUE + "Buy Random Scroll";

    public static Inventory create() {
        Inventory inv = Bukkit.createInventory(null, 9, TITLE);

        // Center button
        inv.setItem(4, createIcon(Material.GOLD_INGOT, ChatColor.YELLOW + "Purchase Scroll"));

        return inv;
    }

    private static ItemStack createIcon(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}
