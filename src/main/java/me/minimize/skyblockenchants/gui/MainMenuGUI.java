package me.minimize.skyblockenchants.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Main menu: buy vs. slot machine
 * Author: MrMiniMize & minimize0x
 */
public final class MainMenuGUI {

    private static final String TITLE = ChatColor.DARK_GREEN + "Skyblock Enchants";

    public static Inventory create() {
        Inventory inv = Bukkit.createInventory(null, 9, TITLE);

        inv.setItem(3, createIcon(Material.EMERALD, ChatColor.GREEN + "Buy Random Scroll"));
        inv.setItem(5, createIcon(Material.DIAMOND, ChatColor.AQUA + "Slot Machine"));

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
