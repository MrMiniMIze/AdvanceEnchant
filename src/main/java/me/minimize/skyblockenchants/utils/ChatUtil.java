package me.minimize.skyblockenchants.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Utility to send color-coded messages.
 * Author: MrMiniMize & minimize0x
 */
public final class ChatUtil {

    private ChatUtil() {}

    public static void send(CommandSender sender, String msg) {
        sender.sendMessage(color(msg));
    }

    public static void log(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(color(msg));
    }

    private static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
