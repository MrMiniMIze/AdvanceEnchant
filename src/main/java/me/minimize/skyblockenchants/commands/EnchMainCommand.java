package me.minimize.skyblockenchants.commands;

import me.minimize.skyblockenchants.SkyblockEnchants;
import me.minimize.skyblockenchants.gui.MainMenuGUI;
import me.minimize.skyblockenchants.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Opens the main GUI.
 * Command name is from the config's "command-alias", default /ench.
 * Author: MrMiniMize & minimize0x
 */
public final class EnchMainCommand implements CommandExecutor {

    private final SkyblockEnchants plugin;

    public EnchMainCommand(SkyblockEnchants plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            ChatUtil.send(sender, "&cOnly players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        player.openInventory(MainMenuGUI.create());
        return true;
    }
}
