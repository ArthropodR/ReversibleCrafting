package me.arthropodr.reversiblecrafting.items.stone;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class StoneItems {

    public static boolean reverseItem(Player player, ItemStack item) {
        switch (item.getType()) {
            case STONE_SWORD:
                player.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 2));
                player.getInventory().addItem(new ItemStack(Material.STICK, 1));
                return true;
            case STONE_PICKAXE:
                player.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 3));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case STONE_AXE:
                player.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 3));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case STONE_SHOVEL:
                player.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            case STONE_HOE:
                player.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 2));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;
            default:
                return false;
        }
    }

    public static void registerStoneItems(JavaPlugin plugin) {
        ItemStack stoneSword = new ItemStack(Material.STONE_SWORD);
        ShapedRecipe stoneSwordRecipe = new ShapedRecipe(stoneSword);
        stoneSwordRecipe.shape(" C ", " C ", " S ");
        stoneSwordRecipe.setIngredient('C', Material.COBBLESTONE);
        stoneSwordRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(stoneSwordRecipe);

        ItemStack stonePickaxe = new ItemStack(Material.STONE_PICKAXE);
        ShapedRecipe stonePickaxeRecipe = new ShapedRecipe(stonePickaxe);
        stonePickaxeRecipe.shape("CCC", " S ", " S ");
        stonePickaxeRecipe.setIngredient('C', Material.COBBLESTONE);
        stonePickaxeRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(stonePickaxeRecipe);

        ItemStack stoneAxe = new ItemStack(Material.STONE_AXE);
        ShapedRecipe stoneAxeRecipe = new ShapedRecipe(stoneAxe);
        stoneAxeRecipe.shape("CC ", "CS ", " S ");
        stoneAxeRecipe.setIngredient('C', Material.COBBLESTONE);
        stoneAxeRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(stoneAxeRecipe);

        ItemStack stoneShovel = new ItemStack(Material.STONE_SHOVEL);
        ShapedRecipe stoneShovelRecipe = new ShapedRecipe(stoneShovel);
        stoneShovelRecipe.shape(" C ", " S ", " S ");
        stoneShovelRecipe.setIngredient('C', Material.COBBLESTONE);
        stoneShovelRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(stoneShovelRecipe);

        ItemStack stoneHoe = new ItemStack(Material.STONE_HOE);
        ShapedRecipe stoneHoeRecipe = new ShapedRecipe(stoneHoe);
        stoneHoeRecipe.shape("CC ", " S ", " S ");
        stoneHoeRecipe.setIngredient('C', Material.COBBLESTONE);
        stoneHoeRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(stoneHoeRecipe);
    }
}