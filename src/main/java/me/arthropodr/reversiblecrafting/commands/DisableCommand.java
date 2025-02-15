package me.arthropodr.reversiblecrafting.commands;

import me.arthropodr.reversiblecrafting.ReversibleCrafting;
import me.arthropodr.reversiblecrafting.utils.DisableManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DisableCommand {
    private final DisableManager disableManager;

    public DisableCommand(ReversibleCrafting plugin) {
        this.disableManager = new DisableManager(plugin);
    }

    public boolean handleCommand(CommandSender sender, String[] args) {
        String action = args[0].toLowerCase();

        switch (action) {
            case "disable":
                if (!sender.hasPermission("reversiblecrafting.disable")) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to disable items!");
                    return true;
                }
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "Usage: /rvc disable <item_name/this>");
                    return true;
                }
                handleDisable(sender, args[1].toUpperCase());
                break;

            case "enable":
                if (!sender.hasPermission("reversiblecrafting.enable")) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to enable items!");
                    return true;
                }
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "Usage: /rvc enable <item_name>");
                    return true;
                }
                handleEnable(sender, args[1].toUpperCase());
                break;

            case "list":
                if (!sender.hasPermission("reversiblecrafting.list")) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to view disabled items!");
                    return true;
                }
                handleList(sender);
                break;

            default:
                sender.sendMessage(ChatColor.RED + "Unknown action. Use disable, enable, or list");
        }

        return true;
    }

    private void handleDisable(CommandSender sender, String itemIdentifier) {
        if (itemIdentifier.equals("THIS") && sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack item = player.getInventory().getItemInMainHand();

            if (item.getType() == Material.AIR) {
                sender.sendMessage(ChatColor.RED + "You must hold an item to disable it!");
                return;
            }

            String identifier;
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasDisplayName()) {
                identifier = meta.getDisplayName();
            } else {
                identifier = item.getType().name();
            }

            disableManager.disableItem(identifier);
            sender.sendMessage(ChatColor.GREEN + "Successfully disabled: " + identifier);
        } else {
            try {
                Material material = Material.valueOf(itemIdentifier);
                disableManager.disableItem(material.name());
                sender.sendMessage(ChatColor.GREEN + "Successfully disabled: " + material.name());
            } catch (IllegalArgumentException e) {
                disableManager.disableItem(itemIdentifier);
                sender.sendMessage(ChatColor.GREEN + "Successfully disabled custom item: " + itemIdentifier);
            }
        }
    }

    private void handleEnable(CommandSender sender, String itemIdentifier) {
        if (disableManager.getDisabledItems().contains(itemIdentifier)) {
            disableManager.enableItem(itemIdentifier);
            sender.sendMessage(ChatColor.GREEN + "Successfully enabled: " + itemIdentifier);
        } else {
            sender.sendMessage(ChatColor.RED + "This item is not disabled!");
        }
    }

    private void handleList(CommandSender sender) {
        List<String> disabledItems = disableManager.getDisabledItems();
        if (disabledItems.isEmpty()) {
            sender.sendMessage(ChatColor.YELLOW + "No items are currently disabled.");
            return;
        }

        sender.sendMessage(ChatColor.GREEN + "Disabled items:");
        for (String item : disabledItems) {
            sender.sendMessage(ChatColor.GRAY + "- " + item);
        }
    }

    public List<String> onTabComplete(String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 2) {
            String action = args[0].toLowerCase();
            String partial = args[1].toUpperCase();

            if (action.equals("disable")) {
                // Add "this" option
                if ("THIS".startsWith(partial)) {
                    completions.add("this");
                }

                // Add material names
                completions.addAll(Arrays.stream(Material.values())
                        .filter(material -> material.isItem() && !material.isAir()) // Only actual items
                        .map(Material::name)
                        .filter(name -> name.startsWith(partial))
                        .collect(Collectors.toList()));

            } else if (action.equals("enable")) {
                // Add disabled items that match the partial input
                completions.addAll(disableManager.getDisabledItems().stream()
                        .filter(item -> item.toUpperCase().startsWith(partial))
                        .collect(Collectors.toList()));
            }
        }

        return completions;
    }
}
