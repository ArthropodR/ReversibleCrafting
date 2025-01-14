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
import java.util.List;

public class RvcGuiCommand implements CommandExecutor, TabCompleter {

    private final ReversibleCrafting plugin;

    public RvcGuiCommand(ReversibleCrafting plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("reversiblecrafting.gui")) {
            player.sendMessage("You don't have permission to use this command.");
            plugin.getLogger().info("Player " + player.getName() + " lacks permission: reversiblecrafting.gui");
            return true;
        }

        if (args.length > 0 && args[0].equalsIgnoreCase("gui")) {
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
        } else {
            player.sendMessage("Usage: /rvc gui");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if ("gui".startsWith(args[0].toLowerCase())) {
                completions.add("gui");
            }
        }

        return completions;
    }
}