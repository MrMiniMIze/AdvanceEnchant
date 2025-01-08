package me.minimize.skyblockenchants.gui;

import me.minimize.skyblockenchants.SkyblockEnchants;
import me.minimize.skyblockenchants.hooks.VaultHook;
import me.minimize.skyblockenchants.scrolls.ScrollManager;
import me.minimize.skyblockenchants.utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Handles clicks in custom GUIs.
 * Author: MrMiniMize & minimize0x
 */
public final class GUIInteractListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView() == null) return;
        if (e.getClickedInventory() == null) return;

        String title = ChatColor.stripColor(e.getView().getTitle());
        Player p = (Player) e.getWhoClicked();

        // MAIN MENU
        if (title.equalsIgnoreCase("Skyblock Enchants")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (!e.getCurrentItem().hasItemMeta()) return;

            String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
            if (name.equalsIgnoreCase("Buy Random Scroll")) {
                p.openInventory(BuyMenuGUI.create());
            } else if (name.equalsIgnoreCase("Slot Machine")) {
                p.openInventory(SlotMachineGUI.create());
            }
        }

        // BUY MENU
        else if (title.equalsIgnoreCase("Buy Random Scroll")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (!e.getCurrentItem().hasItemMeta()) return;
            String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
            if (name.equalsIgnoreCase("Purchase Scroll")) {
                double cost = SkyblockEnchants.getInstance().getConfigManager().getBuyRandomScrollCost();
                if (!chargePlayer(p, cost)) {
                    ChatUtil.send(p, "&cYou cannot afford " + cost);
                    return;
                }
                ScrollManager sm = SkyblockEnchants.getInstance().getScrollManager();
                String tier = sm.getRandomTier("buy-menu-tiers");
                ItemStack scroll = sm.createScroll(tier);
                p.getInventory().addItem(scroll);
                ChatUtil.send(p, "&aYou bought a(n) " + tier + " scroll for " + cost + "!");
            }
        }

        // SLOT MACHINE
        else if (title.equalsIgnoreCase("Scroll Slot Machine")) {
            if (e.getSlot() == 2 || e.getSlot() == 3 || e.getSlot() == 4) {
                // allow placing scrolls
                return;
            }
            e.setCancelled(true);

            // "Spin" at slot 6
            if (e.getSlot() == 6) {
                Inventory inv = e.getInventory();
                ItemStack s2 = inv.getItem(2);
                ItemStack s3 = inv.getItem(3);
                ItemStack s4 = inv.getItem(4);
                ScrollManager sm = SkyblockEnchants.getInstance().getScrollManager();

                if (!sm.isScroll(s2) || !sm.isScroll(s3) || !sm.isScroll(s4)) {
                    ChatUtil.send(p, "&cYou must place 3 scrolls to spin!");
                    return;
                }

                double cost = SkyblockEnchants.getInstance().getConfigManager().getSlotMachineCost();
                if (!chargePlayer(p, cost)) {
                    ChatUtil.send(p, "&cYou cannot afford " + cost);
                    return;
                }

                // Remove the 3 scrolls
                inv.setItem(2, null);
                inv.setItem(3, null);
                inv.setItem(4, null);

                // Randomly pick a new scroll tier
                String tier = sm.getRandomTier("slot-machine-tiers");
                ItemStack newScroll = sm.createScroll(tier);
                p.getInventory().addItem(newScroll);
                ChatUtil.send(p, "&aYou spun the machine and got a(n) " + tier + " scroll!");
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        String title = ChatColor.stripColor(e.getView().getTitle());
        if (title.equalsIgnoreCase("Scroll Slot Machine")) {
            // only slots 2,3,4 are allowed
            for (int slot : e.getRawSlots()) {
                if (slot < 9 && slot != 2 && slot != 3 && slot != 4) {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }

    private boolean chargePlayer(Player p, double amount) {
        if (amount <= 0) return true;
        if (VaultHook.getEconomy().getBalance(p) < amount) {
            return false;
        }
        VaultHook.getEconomy().withdrawPlayer(p, amount);
        return true;
    }
}
