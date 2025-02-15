package me.arthropodr.reversiblecrafting.utils;

import me.arthropodr.reversiblecrafting.ReversibleCrafting;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisableManager {
    private final ReversibleCrafting plugin;
    private final File configFile;
    private FileConfiguration disabledConfig;
    private List<String> disabledItems;

    public DisableManager(ReversibleCrafting plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "disabled-items.yml");
        loadDisabledItems();
    }

    private void loadDisabledItems() {
        if (!configFile.exists()) {
            plugin.saveResource("disabled-items.yml", false);
        }

        disabledConfig = YamlConfiguration.loadConfiguration(configFile);
        disabledItems = disabledConfig.getStringList("disabled-items");
        if (disabledItems == null) {
            disabledItems = new ArrayList<>();
            disabledConfig.set("disabled-items", disabledItems);
            saveDisabledItems();
        }
    }

    private void saveDisabledItems() {
        try {
            disabledConfig.set("disabled-items", disabledItems);
            disabledConfig.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save disabled items to " + configFile);
            e.printStackTrace();
        }
    }

    public boolean isItemDisabled(ItemStack item) {
        if (item == null) return false;

        // Check material name
        if (disabledItems.contains(item.getType().name())) {
            return true;
        }

        // Check custom item name
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName()) {
            String displayName = meta.getDisplayName();
            String strippedName = ChatColor.stripColor(displayName);

            return disabledItems.contains(displayName) ||
                    disabledItems.contains(strippedName);
        }

        return false;
    }

    public void disableItem(String itemIdentifier) {
        // Remove any existing variations of this item (with/without color codes)
        disabledItems.removeIf(item ->
                ChatColor.stripColor(item).equalsIgnoreCase(ChatColor.stripColor(itemIdentifier)));

        // Add the new identifier
        disabledItems.add(itemIdentifier);
        saveDisabledItems();
    }

    public void enableItem(String itemIdentifier) {
        // Remove both exact match and color-stripped match
        boolean removed = disabledItems.remove(itemIdentifier);
        if (!removed) {
            disabledItems.removeIf(item ->
                    ChatColor.stripColor(item).equalsIgnoreCase(ChatColor.stripColor(itemIdentifier)));
        }

        saveDisabledItems();
    }

    public List<String> getDisabledItems() {
        return new ArrayList<>(disabledItems);
    }
}