package me.arthropodr.reversiblecrafting.items.wood;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class WoodItems {

    public static boolean reverseItem(Player player, ItemStack item) {
        switch (item.getType()) {
            case WOODEN_SWORD:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 2));
                player.getInventory().addItem(new ItemStack(Material.STICK, 1));
                return true;
            case WOODEN_PICKAXE:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 3));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case WOODEN_AXE:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 3));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case WOODEN_SHOVEL:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 1));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case WOODEN_HOE:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 2));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            default:
                return false;
        }
    }

    public static void registerWoodItems(JavaPlugin plugin) {
        ItemStack woodenSword = new ItemStack(Material.WOODEN_SWORD);
        ShapedRecipe woodenSwordRecipe = new ShapedRecipe(woodenSword);
        woodenSwordRecipe.shape(" P ", " P ", " S ");
        woodenSwordRecipe.setIngredient('P', Material.OAK_PLANKS);
        woodenSwordRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(woodenSwordRecipe);

        ItemStack woodenPickaxe = new ItemStack(Material.WOODEN_PICKAXE);
        ShapedRecipe woodenPickaxeRecipe = new ShapedRecipe(woodenPickaxe);
        woodenPickaxeRecipe.shape("PPP", " S ", " S ");
        woodenPickaxeRecipe.setIngredient('P', Material.OAK_PLANKS);
        woodenPickaxeRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(woodenPickaxeRecipe);

        ItemStack woodenAxe = new ItemStack(Material.WOODEN_AXE);
        ShapedRecipe woodenAxeRecipe = new ShapedRecipe(woodenAxe);
        woodenAxeRecipe.shape("PP ", "PS ", " S ");
        woodenAxeRecipe.setIngredient('P', Material.OAK_PLANKS);
        woodenAxeRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(woodenAxeRecipe);

        ItemStack woodenShovel = new ItemStack(Material.WOODEN_SHOVEL);
        ShapedRecipe woodenShovelRecipe = new ShapedRecipe(woodenShovel);
        woodenShovelRecipe.shape(" P ", " S ", " S ");
        woodenShovelRecipe.setIngredient('P', Material.OAK_PLANKS);
        woodenShovelRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(woodenShovelRecipe);

        ItemStack woodenHoe = new ItemStack(Material.WOODEN_HOE);
        ShapedRecipe woodenHoeRecipe = new ShapedRecipe(woodenHoe);
        woodenHoeRecipe.shape("PP ", " S ", " S ");
        woodenHoeRecipe.setIngredient('P', Material.OAK_PLANKS);
        woodenHoeRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(woodenHoeRecipe);
    }
}