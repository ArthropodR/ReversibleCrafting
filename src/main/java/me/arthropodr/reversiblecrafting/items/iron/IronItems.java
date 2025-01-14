package me.arthropodr.reversiblecrafting.items.iron;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class IronItems {

    public static boolean reverseItem(Player player, ItemStack item) {
        switch (item.getType()) {
            case IRON_SWORD:
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
                player.getInventory().addItem(new ItemStack(Material.STICK, 1));
                return true;
            case IRON_SHOVEL:
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case IRON_PICKAXE:
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case IRON_AXE:
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case IRON_HOE:
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            default:
                return false;
        }
    }

    public static void registerIronItems(JavaPlugin plugin) {
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
        ShapedRecipe ironSwordRecipe = new ShapedRecipe(ironSword);
        ironSwordRecipe.shape(" I ", " I ", " S ");
        ironSwordRecipe.setIngredient('I', Material.IRON_INGOT);
        ironSwordRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(ironSwordRecipe);

        ItemStack ironShovel = new ItemStack(Material.IRON_SHOVEL);
        ShapedRecipe ironShovelRecipe = new ShapedRecipe(ironShovel);
        ironShovelRecipe.shape(" I ", " S ", " S ");
        ironShovelRecipe.setIngredient('I', Material.IRON_INGOT);
        ironShovelRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(ironShovelRecipe);

        ItemStack ironPickaxe = new ItemStack(Material.IRON_PICKAXE);
        ShapedRecipe ironPickaxeRecipe = new ShapedRecipe(ironPickaxe);
        ironPickaxeRecipe.shape("III", " S ", " S ");
        ironPickaxeRecipe.setIngredient('I', Material.IRON_INGOT);
        ironPickaxeRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(ironPickaxeRecipe);

        ItemStack ironAxe = new ItemStack(Material.IRON_AXE);
        ShapedRecipe ironAxeRecipe = new ShapedRecipe(ironAxe);
        ironAxeRecipe.shape("II ", "IS ", " S ");
        ironAxeRecipe.setIngredient('I', Material.IRON_INGOT);
        ironAxeRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(ironAxeRecipe);

        ItemStack ironHoe = new ItemStack(Material.IRON_HOE);
        ShapedRecipe ironHoeRecipe = new ShapedRecipe(ironHoe);
        ironHoeRecipe.shape("II ", " S ", " S ");
        ironHoeRecipe.setIngredient('I', Material.IRON_INGOT);
        ironHoeRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(ironHoeRecipe);
    }
}