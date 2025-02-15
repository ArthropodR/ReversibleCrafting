package me.arthropodr.reversiblecrafting.commands;

import me.arthropodr.reversiblecrafting.ReversibleCrafting;
import me.arthropodr.reversiblecrafting.gui.ReversibleGui;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RvcGuiCommand implements CommandExecutor, TabCompleter {

    private final ReversibleCrafting plugin;
    private final DisableCommand disableCommand;

    public RvcGuiCommand(ReversibleCrafting plugin) {
        this.plugin = plugin;
        this.disableCommand = new DisableCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /rvc <gui/disable/enable/list>");
            return true;
        }

        String subCommand = args[0].toLowerCase();
        switch (subCommand) {
            case "gui":
                return handleGuiCommand(sender);
            case "disable":
            case "enable":
            case "list":
                return disableCommand.handleCommand(sender, args);
            default:
                sender.sendMessage(ChatColor.RED + "Unknown command. Use: gui, disable, enable, or list");
                return true;
        }
    }

    private boolean handleGuiCommand(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("reversiblecrafting.gui")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            plugin.getLogger().info("Player " + player.getName() + " lacks permission: reversiblecrafting.gui");
            return true;
        }

        try {
            Inventory gui = ReversibleGui.createGui();
            if (gui != null) {
                player.openInventory(gui);
            } else {
                plugin.getLogger().severe("GUI creation returned null");
                player.sendMessage(ChatColor.RED + "An error occurred while opening the GUI");
            }
        } catch (Exception e) {
            plugin.getLogger().severe("Error creating GUI: " + e.getMessage());
            e.printStackTrace();
            player.sendMessage(ChatColor.RED + "An error occurred while opening the GUI");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            List<String> commands = Arrays.asList("gui", "disable", "enable", "list");
            String input = args[0].toLowerCase();
            for (String cmd : commands) {
                if (cmd.startsWith(input)) {
                    completions.add(cmd);
                }
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("disable") ||
                    args[0].equalsIgnoreCase("enable")) {
                return disableCommand.onTabComplete(args);
            }
        }

        return completions;
    }
}