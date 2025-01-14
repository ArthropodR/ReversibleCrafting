package me.arthropodr.reversiblecrafting.items.netherite;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class NetherArmour {
    public static boolean reverseItem(Player player, ItemStack item) {
        switch (item.getType()) {
            case NETHERITE_HELMET:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET, 1));
                player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                return true;
            case NETHERITE_CHESTPLATE:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                return true;
            case NETHERITE_LEGGINGS:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                return true;
            case NETHERITE_BOOTS:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS, 1));
                player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                return true;
            default:
                return false;
        }
    }

    public static void registerNetherArmour(JavaPlugin plugin) {
        SmithingRecipe helmetRecipe = new SmithingRecipe(
                new NamespacedKey(plugin, "netherite_helmet"),
                new ItemStack(Material.NETHERITE_HELMET),
                new RecipeChoice.MaterialChoice(Material.DIAMOND_HELMET),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT)
        );
        plugin.getServer().addRecipe(helmetRecipe);

        SmithingRecipe chestplateRecipe = new SmithingRecipe(
                new NamespacedKey(plugin, "netherite_chestplate"),
                new ItemStack(Material.NETHERITE_CHESTPLATE),
                new RecipeChoice.MaterialChoice(Material.DIAMOND_CHESTPLATE),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT)
        );
        plugin.getServer().addRecipe(chestplateRecipe);

        SmithingRecipe leggingsRecipe = new SmithingRecipe(
                new NamespacedKey(plugin, "netherite_leggings"),
                new ItemStack(Material.NETHERITE_LEGGINGS),
                new RecipeChoice.MaterialChoice(Material.DIAMOND_LEGGINGS),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT)
        );
        plugin.getServer().addRecipe(leggingsRecipe);

        SmithingRecipe bootsRecipe = new SmithingRecipe(
                new NamespacedKey(plugin, "netherite_boots"),
                new ItemStack(Material.NETHERITE_BOOTS),
                new RecipeChoice.MaterialChoice(Material.DIAMOND_BOOTS),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT)
        );
        plugin.getServer().addRecipe(bootsRecipe);
    }
}