package me.arthropodr.reversiblecrafting.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class ReverseUtils {

    public static boolean reverseItem(Player player, ItemStack item) {
        if (item == null || item.getType() == Material.AIR) return false;

        var recipes = Bukkit.getServer().getRecipesFor(new ItemStack(item.getType()));
        if (recipes.isEmpty()) {
            player.sendMessage(ChatColor.RED + "This item cannot be reversed.");
            return false;
        }

        Recipe bestRecipe = findBestRecipe(recipes);
        if (bestRecipe == null) {
            player.sendMessage(ChatColor.RED + "No valid recipe found for this item.");
            return false;
        }

        int resultAmount = bestRecipe.getResult().getAmount();

        if (item.getAmount() < resultAmount) {
            player.sendMessage(ChatColor.RED + "Need at least " + resultAmount + " items to reverse craft.");
            return false;
        }

        int timesToReverse = item.getAmount() / resultAmount;

        Map<Material, Integer> ingredients = new HashMap<>();
        if (bestRecipe instanceof ShapelessRecipe) {
            ((ShapelessRecipe) bestRecipe).getIngredientList().forEach(ingredient -> {
                if (ingredient != null) {
                    ingredients.merge(ingredient.getType(),
                            ingredient.getAmount() * timesToReverse,
                            Integer::sum);
                }
            });
        } else if (bestRecipe instanceof ShapedRecipe) {
            ((ShapedRecipe) bestRecipe).getIngredientMap().values().forEach(ingredient -> {
                if (ingredient != null) {
                    ingredients.merge(ingredient.getType(),
                            ingredient.getAmount() * timesToReverse,
                            Integer::sum);
                }
            });
        }

        if (ingredients.isEmpty()) {
            player.sendMessage(ChatColor.RED + "This recipe has no ingredients to return.");
            return false;
        }

        ingredients.forEach((material, amount) -> {
            ItemStack ingredient = new ItemStack(material, amount);
            Map<Integer, ItemStack> leftover = player.getInventory().addItem(ingredient);
            leftover.values().forEach(stack ->
                    player.getWorld().dropItemNaturally(player.getLocation(), stack));
        });

        int remainingItems = item.getAmount() % resultAmount;
        if (remainingItems > 0) {
            ItemStack remaining = item.clone();
            remaining.setAmount(remainingItems);
            player.getInventory().addItem(remaining).forEach((index, leftover) ->
                    player.getWorld().dropItemNaturally(player.getLocation(), leftover));
        }

        player.sendMessage(ChatColor.GREEN + "Successfully reversed " +
                (timesToReverse * resultAmount) + " items!");
        return true;
    }

    private static Recipe findBestRecipe(Iterable<Recipe> recipes) {
        Recipe bestRecipe = null;
        int bestIngredientCount = 0;

        for (Recipe recipe : recipes) {
            if (recipe instanceof ShapelessRecipe || recipe instanceof ShapedRecipe) {
                int ingredientCount = countIngredients(recipe);
                if (ingredientCount > bestIngredientCount) {
                    bestIngredientCount = ingredientCount;
                    bestRecipe = recipe;
                }
            }
        }

        return bestRecipe;
    }

    private static int countIngredients(Recipe recipe) {
        if (recipe instanceof ShapelessRecipe) {
            return ((ShapelessRecipe) recipe).getIngredientList().size();
        } else if (recipe instanceof ShapedRecipe) {
            return ((ShapedRecipe) recipe).getIngredientMap().size();
        }
        return 0;
    }
}