package me.arthropodr.reversiblecrafting.items.iron;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class IronArmour {

    public static boolean reverseItem(Player player, ItemStack item) {
        switch (item.getType()) {
            case IRON_HELMET:
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 5));
                return true;
            case IRON_CHESTPLATE:
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 8));
                return true;
            case IRON_LEGGINGS:
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 7));
                return true;
            case IRON_BOOTS:
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 4));
                return true;
            default:
                return false;
        }
    }

    public static void registerIronArmour(JavaPlugin plugin) {
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ShapedRecipe ironHelmetRecipe = new ShapedRecipe(ironHelmet);
        ironHelmetRecipe.shape("III", "I I");
        ironHelmetRecipe.setIngredient('I', Material.IRON_INGOT);
        plugin.getServer().addRecipe(ironHelmetRecipe);

        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ShapedRecipe ironChestplateRecipe = new ShapedRecipe(ironChestplate);
        ironChestplateRecipe.shape("I I", "III", "III");
        ironChestplateRecipe.setIngredient('I', Material.IRON_INGOT);
        plugin.getServer().addRecipe(ironChestplateRecipe);

        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ShapedRecipe ironLeggingsRecipe = new ShapedRecipe(ironLeggings);
        ironLeggingsRecipe.shape("III", "I I", "I I");
        ironLeggingsRecipe.setIngredient('I', Material.IRON_INGOT);
        plugin.getServer().addRecipe(ironLeggingsRecipe);

        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
        ShapedRecipe ironBootsRecipe = new ShapedRecipe(ironBoots);
        ironBootsRecipe.shape("I I", "I I");
        ironBootsRecipe.setIngredient('I', Material.IRON_INGOT);
        plugin.getServer().addRecipe(ironBootsRecipe);
    }
}