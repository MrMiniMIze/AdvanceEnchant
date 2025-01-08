package me.minimize.skyblockenchants.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * 9-slot inventory for the slot machine: place 3 scrolls, spin
 * Author: MrMiniMize & minimize0x
 */
public final class SlotMachineGUI {

    private static final String TITLE = ChatColor.LIGHT_PURPLE + "Scroll Slot Machine";

    public static Inventory create() {
        Inventory inv = Bukkit.createInventory(null, 9, TITLE);

        // Place placeholders
        inv.setItem(0, createPane("&7Place 3 Scrolls in slots 2,3,4"));
        inv.setItem(1, createPane("&7..."));
        inv.setItem(5, createPane("&7..."));
        inv.setItem(7, createPane("&7..."));
        inv.setItem(8, createPane("&7..."));

        // Spin button at slot 6
        inv.setItem(6, createIcon(Material.LEVER, ChatColor.YELLOW + "SPIN"));
        return inv;
    }

    private static ItemStack createPane(String name) {
        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        pane.setItemMeta(meta);
        return pane;
    }

    private static ItemStack createIcon(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}
