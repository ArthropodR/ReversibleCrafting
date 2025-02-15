package me.arthropodr.reversiblecrafting.utils;

import me.arthropodr.reversiblecrafting.ReversibleCrafting;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.HashMap;
import java.util.Map;

public class ReverseUtils {

    private static final Map<Material, ItemStack[]> customReverseRecipes = new HashMap<>();

    static {
        customReverseRecipes.putAll(NetheriteItems.getNetheriteRecipes());
    }

    public static boolean reverseItem(Player player, ItemStack item) {
        if (item == null || item.getType() == Material.AIR) return false;

        DisableManager disableManager = new DisableManager(ReversibleCrafting.getInstance());
        if (disableManager.isItemDisabled(item)) {
            player.sendMessage(ChatColor.RED + "This item cannot be reversed.");
            return false;
        }

        Material itemType = item.getType();
        ItemStack originalItem = item.clone(); // Clone the original item to preserve its data

        if (customReverseRecipes.containsKey(itemType)) {
            giveReversedItems(player, originalItem, customReverseRecipes.get(itemType));
            return true;
        }

        var recipes = Bukkit.getServer().getRecipesFor(new ItemStack(itemType));
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
        if (originalItem.getAmount() < resultAmount) {
            player.sendMessage(ChatColor.RED + "Need at least " + resultAmount + " items to reverse craft.");
            return false;
        }

        int timesToReverse = originalItem.getAmount() / resultAmount;

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

        // First verify if player has the item before removing anything
        ItemStack itemToRemove = originalItem.clone();
        itemToRemove.setAmount(timesToReverse * resultAmount);

        ingredients.forEach((material, amount) -> {
            ItemStack ingredient = new ItemStack(material, amount);
            Map<Integer, ItemStack> leftover = player.getInventory().addItem(ingredient);
            leftover.values().forEach(stack ->
                    player.getWorld().dropItemNaturally(player.getLocation(), stack));
        });

        player.sendMessage(ChatColor.GREEN + "Successfully reversed " + (timesToReverse * resultAmount) + " items!");
        return true;
    }

    private static void removeExactItem(Player player, ItemStack originalItem, int amountToRemove) {
        int remainingToRemove = amountToRemove;
        ItemStack[] contents = player.getInventory().getContents();

        for (int i = 0; i < contents.length && remainingToRemove > 0; i++) {
            ItemStack invItem = contents[i];
            if (invItem != null && invItem.isSimilar(originalItem)) {
                if (invItem.getAmount() > remainingToRemove) {
                    invItem.setAmount(invItem.getAmount() - remainingToRemove);
                    remainingToRemove = 0;
                } else {
                    remainingToRemove -= invItem.getAmount();
                    player.getInventory().setItem(i, null);
                }
            }
        }
        player.updateInventory();
    }

    private static void giveReversedItems(Player player, ItemStack item, ItemStack[] results) {
        // First verify if player has the item
        if (!player.getInventory().containsAtLeast(item, 1)) {
            player.sendMessage(ChatColor.RED + "Could not find the required item in your inventory.");
            return;
        }

        // Remove the exact item that was used
        removeExactItem(player, item, 1);

        // Give the results
        for (ItemStack result : results) {
            player.getInventory().addItem(result).values()
                    .forEach(leftover -> player.getWorld().dropItemNaturally(player.getLocation(), leftover));
        }

        player.sendMessage(ChatColor.GREEN + "Successfully reversed " + item.getType().name() + "!");
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