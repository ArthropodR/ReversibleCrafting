package me.arthropodr.reversiblecrafting.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReversibleGui {
    private static final String GUI_TITLE = ChatColor.DARK_GREEN + "Reversible Crafting";

    public static Inventory createGui() {
        try {
            Material.valueOf("GRAY_STAINED_GLASS_PANE");
            Material.valueOf("GREEN_STAINED_GLASS_PANE");
            Material.valueOf("RED_STAINED_GLASS_PANE");
        } catch (IllegalArgumentException e) {
            Bukkit.getLogger().severe("One or more required materials not found - check server version compatibility");
            return null;
        }

        Inventory gui = Bukkit.createInventory(null, 54, GUI_TITLE);

        List<Integer> grayGlassSlots = Arrays.asList(
                0, 1, 2, 3, 4, 5, 6, 7, 8,
                9, 17,
                18, 26,
                27, 35,
                36, 44,
                45, 46, 47,
                49, 51, 52, 53
        );

        ItemStack grayGlass = createGlass(Material.GRAY_STAINED_GLASS_PANE, ChatColor.GRAY + "");
        ItemStack greenGlass = createGlass(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + "CONFIRM REVERSE");
        ItemStack redGlass = createGlass(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "CANCEL");

        for (int slot : grayGlassSlots) {
            gui.setItem(slot, grayGlass.clone());
        }

        gui.setItem(48, greenGlass);
        gui.setItem(50, redGlass);

        return gui;
    }

    private static ItemStack createGlass(Material material, String name) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(Collections.singletonList(ChatColor.GRAY + "Click to perform action"));
            item.setItemMeta(meta);
        }
        return item;
    }

    public static String getGuiTitle() {
        return GUI_TITLE;
    }
}