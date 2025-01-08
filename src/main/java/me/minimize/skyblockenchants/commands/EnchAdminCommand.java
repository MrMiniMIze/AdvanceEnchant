package me.minimize.skyblockenchants.commands;

import me.minimize.skyblockenchants.SkyblockEnchants;
import me.minimize.skyblockenchants.enchants.CustomEnchant;
import me.minimize.skyblockenchants.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

/**
 * Staff commands: /enchadmin reload, /enchadmin listench
 * Author: MrMiniMize & minimize0x
 */
public final class EnchAdminCommand implements CommandExecutor {

    private final SkyblockEnchants plugin;

    public EnchAdminCommand(SkyblockEnchants plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

        if (!sender.hasPermission("skyblockenchants.staff")) {
            ChatUtil.send(sender, "&cYou do not have permission for this.");
            return true;
        }

        if (args.length == 0) {
            ChatUtil.send(sender, "&eUsage: /enchadmin <reload | listench>");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.getConfigManager().reload();
            ChatUtil.send(sender, "&aConfig reloaded!");
        }
        else if (args[0].equalsIgnoreCase("listench")) {
            Map<String, CustomEnchant> all = plugin.getEnchantManager().getAllEnchants();
            ChatUtil.send(sender, "&7----- &aAll Custom Enchants &7-----");
            for (CustomEnchant ce : all.values()) {
                ChatUtil.send(sender, "&e" + ce.getKey() + " &7(Tier=" + ce.getTier()
                        + ", MaxLv=" + ce.getMaxLevel() + ") -> " + ce.getEffectDescription());
            }
        }
        else {
            ChatUtil.send(sender, "&cUnknown subcommand. Try /enchadmin reload or /enchadmin listench");
        }

        return true;
    }
}
