package me.arthropodr.reversiblecrafting.listeners;

import me.arthropodr.reversiblecrafting.ReversibleCrafting;
import me.arthropodr.reversiblecrafting.utils.ReverseUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class GuiListener implements Listener {
    private final ReversibleCrafting plugin;

    // List of allowed empty slots
    private final List<Integer> emptySlots = Arrays.asList(
            10, 11, 12, 13, 14, 15, 16,
            19, 20, 21, 22, 23, 24, 25,
            28, 29, 30, 31, 32, 33, 34,
            37, 38, 39, 40, 41, 42, 43
    );

    public GuiListener(ReversibleCrafting plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        // Check if the inventory is the Reversible Crafting GUI
        if (!ChatColor.stripColor(event.getView().getTitle()).equals("Reversible Crafting")) return;

        // Get the player and clicked slot
        Player player = (Player) event.getWhoClicked();
        int clickedSlot = event.getRawSlot();

        // Allow the event if the clicked slot is outside the GUI (player's inventory)
        if (clickedSlot >= inventory.getSize()) return;

        // Allow interaction only for empty slots
        if (emptySlots.contains(clickedSlot)) {
            event.setCancelled(false); // Allow taking and placing items in these slots
            return;
        }

        // Prevent interaction with glass panels and buttons
        event.setCancelled(true);

        ItemStack currentItem = event.getCurrentItem();
        if (currentItem == null || currentItem.getType() == Material.AIR) return;

        // Handle red glass (CANCEL)
        if (currentItem.getType() == Material.RED_STAINED_GLASS_PANE) {
            player.closeInventory();
            player.sendMessage(ChatColor.RED + "Reversible crafting canceled!");
            // Return items in the empty slots to the player
            for (int slot : emptySlots) {
                ItemStack item = inventory.getItem(slot);
                if (item != null && item.getType() != Material.AIR) {
                    player.getInventory().addItem(item);
                    inventory.setItem(slot, null); // Clear the slot in the GUI
                }
            }
        }

        // Handle green glass (CONFIRM REVERSE)
        if (currentItem.getType() == Material.GREEN_STAINED_GLASS_PANE) {
            ReverseUtils.reverseItems(player, inventory);
        }
    }
}