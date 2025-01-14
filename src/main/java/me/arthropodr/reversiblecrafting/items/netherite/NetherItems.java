package me.arthropodr.reversiblecrafting.items.netherite;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class NetherItems {

    public static boolean reverseItem(Player player, ItemStack item) {
        switch (item.getType()) {
            case NETHERITE_SWORD:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
                player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                return true;
            case NETHERITE_PICKAXE:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE, 1));
                player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                return true;
            case NETHERITE_AXE:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE, 1));
                player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                return true;
            case NETHERITE_SHOVEL:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_SHOVEL, 1));
                player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                return true;
            case NETHERITE_HOE:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_HOE, 1));
                player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                return true;
            default:
                return false;
        }
    }

    public static void registerNetherItems(JavaPlugin plugin) {
        SmithingRecipe swordRecipe = new SmithingRecipe(
                new NamespacedKey(plugin, "netherite_sword"),
                new ItemStack(Material.NETHERITE_SWORD),
                new RecipeChoice.MaterialChoice(Material.DIAMOND_SWORD),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT)
        );
        plugin.getServer().addRecipe(swordRecipe);

        SmithingRecipe pickaxeRecipe = new SmithingRecipe(
                new NamespacedKey(plugin, "netherite_pickaxe"),
                new ItemStack(Material.NETHERITE_PICKAXE),
                new RecipeChoice.MaterialChoice(Material.DIAMOND_PICKAXE),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT)
        );
        plugin.getServer().addRecipe(pickaxeRecipe);

        SmithingRecipe axeRecipe = new SmithingRecipe(
                new NamespacedKey(plugin, "netherite_axe"),
                new ItemStack(Material.NETHERITE_AXE),
                new RecipeChoice.MaterialChoice(Material.DIAMOND_AXE),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT)
        );
        plugin.getServer().addRecipe(axeRecipe);

        SmithingRecipe shovelRecipe = new SmithingRecipe(
                new NamespacedKey(plugin,"netherite_shovel"),
                new ItemStack(Material.NETHERITE_SHOVEL),
                new RecipeChoice.MaterialChoice(Material.DIAMOND_SHOVEL),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT)
        );
        plugin.getServer().addRecipe(shovelRecipe);

        SmithingRecipe hoeRecipe = new SmithingRecipe(
                new NamespacedKey(plugin, "netherite_hoe"),
                new ItemStack(Material.NETHERITE_HOE),
                new RecipeChoice.MaterialChoice(Material.DIAMOND_HOE),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT)
        );
        plugin.getServer().addRecipe(hoeRecipe);
    }
}