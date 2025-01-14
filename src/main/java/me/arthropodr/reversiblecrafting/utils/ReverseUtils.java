package me.arthropodr.reversiblecrafting.utils;

import me.arthropodr.reversiblecrafting.items.netherite.NetherItems;
import me.arthropodr.reversiblecrafting.items.netherite.NetherArmour;
import me.arthropodr.reversiblecrafting.items.diamond.DiamondItems;
import me.arthropodr.reversiblecrafting.items.diamond.DiamondArmour;
import me.arthropodr.reversiblecrafting.items.iron.IronItems;
import me.arthropodr.reversiblecrafting.items.iron.IronArmour;
import me.arthropodr.reversiblecrafting.items.stone.StoneItems;
import me.arthropodr.reversiblecrafting.items.wood.WoodItems;
import me.arthropodr.reversiblecrafting.items.wood.LeatherArmour;
import me.arthropodr.reversiblecrafting.items.wood.Planks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ReverseUtils {

    public static void reverseItems(Player player, Inventory inventory) {
        boolean success = false;

        // Iterate through inventory slots and delegate reversal logic
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType() == Material.GRAY_STAINED_GLASS_PANE) continue;

            // Delegate reversal to specific item classes
            if (DiamondItems.reverseItem(player, item) ||
                    DiamondArmour.reverseItem(player, item) ||
                    IronItems.reverseItem(player, item) ||
                    IronArmour.reverseItem(player, item) ||
                    NetherItems.reverseItem(player, item) ||
                    NetherArmour.reverseItem(player, item) ||
                    StoneItems.reverseItem(player, item) ||
                    WoodItems.reverseItem(player, item) ||
                    LeatherArmour.reverseItem(player, item) ||
                    Planks.reverseItem(player, item)) {
                inventory.setItem(i, null);
                success = true;
            }
        }

        if (success) {
            player.sendMessage(ChatColor.GREEN + "Items reversed successfully!");
            player.closeInventory();
        } else {
            player.sendMessage(ChatColor.RED + "No reversible items found!");
        }
    }
}