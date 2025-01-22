package me.arthropodr.reversiblecrafting.listeners;

import me.arthropodr.reversiblecrafting.ReversibleCrafting;
import me.arthropodr.reversiblecrafting.gui.ReversibleGui;
import me.arthropodr.reversiblecrafting.utils.ReverseUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class GuiListener implements Listener {
    private final ReversibleCrafting plugin;
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
        if (!event.getView().getTitle().equals(ReversibleGui.getGuiTitle())) return;

        Player player = (Player) event.getWhoClicked();
        int clickedSlot = event.getRawSlot();

        if (clickedSlot >= event.getView().getTopInventory().getSize()) {
            event.setCancelled(false);
            return;
        }

        if (!emptySlots.contains(clickedSlot)) {
            event.setCancelled(true);
        }

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        if (clickedItem.getType() == Material.RED_STAINED_GLASS_PANE) {
            returnItemsToPlayer(player, event.getInventory().getContents());
            player.closeInventory();
            player.sendMessage(ChatColor.RED + "Reversible crafting process has been cancelled.");
        } else if (clickedItem.getType() == Material.GREEN_STAINED_GLASS_PANE) {
            boolean anyReversed = false;
            for (int slot : emptySlots) {
                ItemStack itemInSlot = event.getInventory().getItem(slot);
                if (itemInSlot != null && itemInSlot.getType() != Material.AIR) {
                    if (ReverseUtils.reverseItem(player, itemInSlot.clone())) {
                        event.getInventory().setItem(slot, null);
                        anyReversed = true;
                    }
                }
            }
            if (!anyReversed) {
                player.sendMessage(ChatColor.RED + "No items could be reversed.");
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!event.getView().getTitle().equals(ReversibleGui.getGuiTitle())) return;

        for (int slot : event.getRawSlots()) {
            if (slot < event.getView().getTopInventory().getSize() && !emptySlots.contains(slot)) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!event.getView().getTitle().equals(ReversibleGui.getGuiTitle())) return;

        returnItemsToPlayer((Player) event.getPlayer(), event.getInventory().getContents());
    }

    private void returnItemsToPlayer(Player player, ItemStack[] contents) {
        for (int slot : emptySlots) {
            ItemStack item = contents[slot];
            if (item != null && item.getType() != Material.AIR) {
                player.getInventory().addItem(item.clone()).forEach((index, leftover) ->
                        player.getWorld().dropItemNaturally(player.getLocation(), leftover));
            }
        }
    }
}