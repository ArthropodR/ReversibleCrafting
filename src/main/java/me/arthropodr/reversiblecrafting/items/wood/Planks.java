package me.arthropodr.reversiblecrafting.items.wood;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class Planks {

    public static boolean reverseItem(Player player, ItemStack item) {
        switch (item.getType()) {
            case OAK_PLANKS:
                player.getInventory().addItem(new ItemStack(Material.OAK_LOG, 1));
                return true;

            case STICK:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 2));
                return true;

            case CHEST:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 8));
                return true;

            case OAK_DOOR:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 6));
                return true;

            case OAK_BOAT:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 5));
                return true;

            case OAK_SIGN:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 6));
                player.getInventory().addItem(new ItemStack(Material.STICK, 1));
                return true;

            case BOWL:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 3));
                return true;

            case OAK_STAIRS:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 6));
                return true;

            case OAK_SLAB:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 3));
                return true;

            case OAK_FENCE:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 4));
                player.getInventory().addItem(new ItemStack(Material.STICK, 2));
                return true;

            case OAK_TRAPDOOR:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 6));
                return true;

            case CRAFTING_TABLE:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 4));
                return true;

            case BOOKSHELF:
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 6));
                player.getInventory().addItem(new ItemStack(Material.BOOK, 3));
                return true;

            case LADDER:
                player.getInventory().addItem(new ItemStack(Material.STICK, 7));
                return true;

            case OAK_FENCE_GATE:
                player.getInventory().addItem(new ItemStack(Material.STICK, 4));
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 2));
                return true;

            default:
                return false;
        }
    }

    public static void registerPlankRecipes(JavaPlugin plugin) {
        ItemStack oakPlanks = new ItemStack(Material.OAK_PLANKS, 4);
        ShapedRecipe oakPlankRecipe = new ShapedRecipe(oakPlanks);
        oakPlankRecipe.shape("L");
        oakPlankRecipe.setIngredient('L', Material.OAK_LOG);
        plugin.getServer().addRecipe(oakPlankRecipe);

        ItemStack stick = new ItemStack(Material.STICK, 4);
        ShapedRecipe stickRecipe = new ShapedRecipe(stick);
        stickRecipe.shape("P", "P");
        stickRecipe.setIngredient('P', Material.OAK_PLANKS);
        plugin.getServer().addRecipe(stickRecipe);

        ItemStack chest = new ItemStack(Material.CHEST);
        ShapedRecipe chestRecipe = new ShapedRecipe(chest);
        chestRecipe.shape("PPP", "P P", "PPP");
        chestRecipe.setIngredient('P', Material.OAK_PLANKS);
        plugin.getServer().addRecipe(chestRecipe);

        ItemStack oakDoor = new ItemStack(Material.OAK_DOOR, 3);
        ShapedRecipe oakDoorRecipe = new ShapedRecipe(oakDoor);
        oakDoorRecipe.shape("PP", "PP", "PP");
        oakDoorRecipe.setIngredient('P', Material.OAK_PLANKS);
        plugin.getServer().addRecipe(oakDoorRecipe);

        ItemStack oakBoat = new ItemStack(Material.OAK_BOAT, 1);
        ShapedRecipe oakBoatRecipe = new ShapedRecipe(oakBoat);
        oakBoatRecipe.shape("P P", "PPP");
        oakBoatRecipe.setIngredient('P', Material.OAK_PLANKS);
        plugin.getServer().addRecipe(oakBoatRecipe);

        ItemStack oakSign = new ItemStack(Material.OAK_SIGN, 1);
        ShapedRecipe oakSignRecipe = new ShapedRecipe(oakSign);
        oakSignRecipe.shape("PPP", "S S");
        oakSignRecipe.setIngredient('P', Material.OAK_PLANKS);
        oakSignRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(oakSignRecipe);

        ItemStack bookshelf = new ItemStack(Material.BOOKSHELF);
        ShapedRecipe bookshelfRecipe = new ShapedRecipe(bookshelf);
        bookshelfRecipe.shape("PPP", "BBB", "PPP");
        bookshelfRecipe.setIngredient('P', Material.OAK_PLANKS);
        bookshelfRecipe.setIngredient('B', Material.BOOK);
        plugin.getServer().addRecipe(bookshelfRecipe);

        ItemStack craftingTable = new ItemStack(Material.CRAFTING_TABLE);
        ShapedRecipe craftingTableRecipe = new ShapedRecipe(craftingTable);
        craftingTableRecipe.shape("PP", "PP");
        craftingTableRecipe.setIngredient('P', Material.OAK_PLANKS);
        plugin.getServer().addRecipe(craftingTableRecipe);

        ItemStack ladder = new ItemStack(Material.LADDER, 3);
        ShapedRecipe ladderRecipe = new ShapedRecipe(ladder);
        ladderRecipe.shape("S S", "SSS", "S S");
        ladderRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(ladderRecipe);

        ItemStack oakFence = new ItemStack(Material.OAK_FENCE, 3);
        ShapedRecipe oakFenceRecipe = new ShapedRecipe(oakFence);
        oakFenceRecipe.shape("PSP", "PSP");
        oakFenceRecipe.setIngredient('P', Material.OAK_PLANKS);
        oakFenceRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(oakFenceRecipe);
    }
}