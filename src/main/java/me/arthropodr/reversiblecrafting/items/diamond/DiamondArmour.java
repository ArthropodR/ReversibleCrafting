package me.arthropodr.reversiblecrafting.items.diamond;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class DiamondArmour {

    public static boolean reverseItem(Player player, ItemStack item) {
        switch (item.getType()) {
            case DIAMOND_HELMET:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND, 5));
                return true;
            case DIAMOND_CHESTPLATE:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND, 8));
                return true;
            case DIAMOND_LEGGINGS:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND, 7));
                return true;
            case DIAMOND_BOOTS:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND, 4));
                return true;
            default:
                return false;
        }
    }

    public static void registerDiamondArmour(JavaPlugin plugin) {
        ItemStack diamondHelmet = new ItemStack(Material.DIAMOND_HELMET);
        ShapedRecipe diamondHelmetRecipe = new ShapedRecipe(diamondHelmet);
        diamondHelmetRecipe.shape("DDD", "D D");
        diamondHelmetRecipe.setIngredient('D', Material.DIAMOND);
        plugin.getServer().addRecipe(diamondHelmetRecipe);

        ItemStack diamondChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ShapedRecipe diamondChestplateRecipe = new ShapedRecipe(diamondChestplate);
        diamondChestplateRecipe.shape("D D", "DDD", "DDD");
        diamondChestplateRecipe.setIngredient('D', Material.DIAMOND);
        plugin.getServer().addRecipe(diamondChestplateRecipe);

        ItemStack diamondLeggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ShapedRecipe diamondLeggingsRecipe = new ShapedRecipe(diamondLeggings);
        diamondLeggingsRecipe.shape("DDD", "D D", "D D");
        diamondLeggingsRecipe.setIngredient('D', Material.DIAMOND);
        plugin.getServer().addRecipe(diamondLeggingsRecipe);

        ItemStack diamondBoots = new ItemStack(Material.DIAMOND_BOOTS);
        ShapedRecipe diamondBootsRecipe = new ShapedRecipe(diamondBoots);
        diamondBootsRecipe.shape("D D", "D D");
        diamondBootsRecipe.setIngredient('D', Material.DIAMOND);
        plugin.getServer().addRecipe(diamondBootsRecipe);
    }
}