package me.arthropodr.reversiblecrafting.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class NetheriteItems {

    private static final Map<Material, ItemStack[]> netheriteReverseRecipes = new HashMap<>();

    static {
        netheriteReverseRecipes.put(Material.NETHERITE_SWORD, new ItemStack[]{
                new ItemStack(Material.NETHERITE_INGOT, 1),
                new ItemStack(Material.DIAMOND_SWORD, 1)
        });

        netheriteReverseRecipes.put(Material.NETHERITE_PICKAXE, new ItemStack[]{
                new ItemStack(Material.NETHERITE_INGOT, 1),
                new ItemStack(Material.DIAMOND_PICKAXE, 1)
        });

        netheriteReverseRecipes.put(Material.NETHERITE_CHESTPLATE, new ItemStack[]{
                new ItemStack(Material.NETHERITE_INGOT, 1),
                new ItemStack(Material.DIAMOND_CHESTPLATE, 1)
        });

        netheriteReverseRecipes.put(Material.NETHERITE_HELMET, new ItemStack[]{
                new ItemStack(Material.NETHERITE_INGOT, 1),
                new ItemStack(Material.DIAMOND_HELMET, 1)
        });

        netheriteReverseRecipes.put(Material.NETHERITE_LEGGINGS, new ItemStack[]{
                new ItemStack(Material.NETHERITE_INGOT, 1),
                new ItemStack(Material.DIAMOND_LEGGINGS, 1)
        });

        netheriteReverseRecipes.put(Material.NETHERITE_BOOTS, new ItemStack[]{
                new ItemStack(Material.NETHERITE_INGOT, 1),
                new ItemStack(Material.DIAMOND_BOOTS, 1)
        });

        netheriteReverseRecipes.put(Material.NETHERITE_AXE, new ItemStack[]{
                new ItemStack(Material.NETHERITE_INGOT, 1),
                new ItemStack(Material.DIAMOND_AXE, 1)
        });

        netheriteReverseRecipes.put(Material.NETHERITE_SHOVEL, new ItemStack[]{
                new ItemStack(Material.NETHERITE_INGOT, 1),
                new ItemStack(Material.DIAMOND_SHOVEL, 1)
        });

        netheriteReverseRecipes.put(Material.NETHERITE_HOE, new ItemStack[]{
                new ItemStack(Material.NETHERITE_INGOT, 1),
                new ItemStack(Material.DIAMOND_HOE, 1)
        });
    }

    public static Map<Material, ItemStack[]> getNetheriteRecipes() {
        return netheriteReverseRecipes;
    }
}