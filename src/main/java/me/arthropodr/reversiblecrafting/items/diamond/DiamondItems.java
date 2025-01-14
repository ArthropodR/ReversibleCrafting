package me.arthropodr.reversiblecrafting.items.diamond;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class DiamondItems {

    public static void registerDiamondItems(JavaPlugin plugin) {
        registerDiamondPickaxeRecipe(plugin);
        registerDiamondAxeRecipe(plugin);
        registerDiamondShovelRecipe(plugin);
        registerDiamondHoeRecipe(plugin);
        registerDiamondSwordRecipe(plugin);
    }

    private static void registerDiamondPickaxeRecipe(JavaPlugin plugin) {
        ItemStack diamonds = new ItemStack(Material.DIAMOND, 3);
        NamespacedKey key = new NamespacedKey(plugin, "uncraft_diamond_pickaxe");

        ShapelessRecipe recipe = new ShapelessRecipe(key, diamonds);
        recipe.addIngredient(1, Material.DIAMOND_PICKAXE);

        plugin.getServer().addRecipe(recipe);
    }

    private static void registerDiamondAxeRecipe(JavaPlugin plugin) {
        ItemStack diamonds = new ItemStack(Material.DIAMOND, 3);
        NamespacedKey key = new NamespacedKey(plugin, "uncraft_diamond_axe");

        ShapelessRecipe recipe = new ShapelessRecipe(key, diamonds);
        recipe.addIngredient(1, Material.DIAMOND_AXE);

        plugin.getServer().addRecipe(recipe);
    }

    private static void registerDiamondShovelRecipe(JavaPlugin plugin) {
        ItemStack diamonds = new ItemStack(Material.DIAMOND, 1);
        NamespacedKey key = new NamespacedKey(plugin, "uncraft_diamond_shovel");

        ShapelessRecipe recipe = new ShapelessRecipe(key, diamonds);
        recipe.addIngredient(1, Material.DIAMOND_SHOVEL);

        plugin.getServer().addRecipe(recipe);
    }

    private static void registerDiamondHoeRecipe(JavaPlugin plugin) {
        ItemStack diamonds = new ItemStack(Material.DIAMOND, 2);
        NamespacedKey key = new NamespacedKey(plugin, "uncraft_diamond_hoe");

        ShapelessRecipe recipe = new ShapelessRecipe(key, diamonds);
        recipe.addIngredient(1, Material.DIAMOND_HOE);

        plugin.getServer().addRecipe(recipe);
    }

    private static void registerDiamondSwordRecipe(JavaPlugin plugin) {
        ItemStack diamonds = new ItemStack(Material.DIAMOND, 2);
        NamespacedKey key = new NamespacedKey(plugin, "uncraft_diamond_sword");

        ShapelessRecipe recipe = new ShapelessRecipe(key, diamonds);
        recipe.addIngredient(1, Material.DIAMOND_SWORD);

        plugin.getServer().addRecipe(recipe);
    }

    public static boolean reverseItem(Player player, ItemStack item) {
        switch (item.getType()) {
            case DIAMOND_SWORD:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
                player.getInventory().addItem(new ItemStack(Material.STICK, 1));
                return true;
            case DIAMOND_SHOVEL:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case DIAMOND_PICKAXE:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case DIAMOND_AXE:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case DIAMOND_HOE:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            default:
                return false;
        }
    }
}