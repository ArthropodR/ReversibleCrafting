package me.arthropodr.reversiblecrafting.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ReversibleGui {

    public static Inventory createGui() {
        try {
            Material.valueOf("GRAY_STAINED_GLASS_PANE");
            Material.valueOf("GREEN_STAINED_GLASS_PANE");
            Material.valueOf("RED_STAINED_GLASS_PANE");
        } catch (IllegalArgumentException e) {
            Bukkit.getLogger().severe("One or more required materials not found - check server version compatibility");
            return null;
        }

        Inventory gui = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "Reversible Crafting");

        List<Integer> grayGlassSlots = Arrays.asList(
                0, 1, 2, 3, 4, 5, 6, 7, 8,   // Row 1
                9, 17,                       // Sides of Row 2
                18, 26,                      // Sides of Row 3
                27, 35,                      // Sides of Row 4
                36, 44,                      // Sides of Row 5
                45, 46, 47,                  // Bottom left
                49, 51, 52, 53              // Bottom right (with gap for panels)
        );

        ItemStack grayGlass = createGlass(Material.GRAY_STAINED_GLASS_PANE, ChatColor.RESET + "");
        for (int slot : grayGlassSlots) {
            gui.setItem(slot, grayGlass);
        }

        ItemStack greenGlass = createGlass(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + "CONFIRM REVERSE");
        gui.setItem(48, greenGlass);

        ItemStack redGlass = createGlass(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "CANCEL");
        gui.setItem(50, redGlass);

        return gui;
    }

    private static ItemStack createGlass(Material material, String name) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }
}