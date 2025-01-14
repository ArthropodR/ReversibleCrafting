package me.arthropodr.reversiblecrafting.items.wood;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class LeatherArmour {

    public static boolean reverseItem(Player player, ItemStack item) {
        switch (item.getType()) {
            case LEATHER_HELMET:
                player.getInventory().addItem(new ItemStack(Material.LEATHER, 5));
                return true;
            case LEATHER_CHESTPLATE:
                player.getInventory().addItem(new ItemStack(Material.LEATHER, 8));
                return true;
            case LEATHER_LEGGINGS:
                player.getInventory().addItem(new ItemStack(Material.LEATHER, 7));
                return true;
            case LEATHER_BOOTS:
                player.getInventory().addItem(new ItemStack(Material.LEATHER, 4));
                return true;
            default:
                return false;
        }
    }

    public static void registerLeatherArmour(JavaPlugin plugin) {
        ItemStack leatherHelmet = new ItemStack(Material.LEATHER_HELMET);
        ShapedRecipe leatherHelmetRecipe = new ShapedRecipe(leatherHelmet);
        leatherHelmetRecipe.shape("LLL", "L L");
        leatherHelmetRecipe.setIngredient('L', Material.LEATHER);
        plugin.getServer().addRecipe(leatherHelmetRecipe);

        ItemStack leatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ShapedRecipe leatherChestplateRecipe = new ShapedRecipe(leatherChestplate);
        leatherChestplateRecipe.shape("L L", "LLL", "LLL");
        leatherChestplateRecipe.setIngredient('L', Material.LEATHER);
        plugin.getServer().addRecipe(leatherChestplateRecipe);

        ItemStack leatherLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ShapedRecipe leatherLeggingsRecipe = new ShapedRecipe(leatherLeggings);
        leatherLeggingsRecipe.shape("LLL", "L L", "L L");
        leatherLeggingsRecipe.setIngredient('L', Material.LEATHER);
        plugin.getServer().addRecipe(leatherLeggingsRecipe);

        ItemStack leatherBoots = new ItemStack(Material.LEATHER_BOOTS);
        ShapedRecipe leatherBootsRecipe = new ShapedRecipe(leatherBoots);
        leatherBootsRecipe.shape("L L", "L L");
        leatherBootsRecipe.setIngredient('L', Material.LEATHER);
        plugin.getServer().addRecipe(leatherBootsRecipe);
    }
}