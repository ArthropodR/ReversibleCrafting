package me.arthropodr.reversiblecrafting;

import me.arthropodr.reversiblecrafting.commands.RvcGuiCommand;
import me.arthropodr.reversiblecrafting.listeners.GuiListener;
import me.arthropodr.reversiblecrafting.items.diamond.DiamondItems;
import me.arthropodr.reversiblecrafting.items.diamond.DiamondArmour;
import me.arthropodr.reversiblecrafting.items.iron.IronItems;
import me.arthropodr.reversiblecrafting.items.iron.IronArmour;
import me.arthropodr.reversiblecrafting.items.stone.StoneItems;
import me.arthropodr.reversiblecrafting.items.wood.WoodItems;
import me.arthropodr.reversiblecrafting.items.wood.LeatherArmour;
import me.arthropodr.reversiblecrafting.items.netherite.NetherItems;
import me.arthropodr.reversiblecrafting.items.netherite.NetherArmour;
import me.arthropodr.reversiblecrafting.items.wood.Planks;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class ReversibleCrafting extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Reversible Crafting has been enabled!");

        RvcGuiCommand rvcGuiCommand = new RvcGuiCommand(this);

        PluginCommand command = getCommand("rvc");
        if (command != null) {
            command.setExecutor(rvcGuiCommand);
            command.setTabCompleter(rvcGuiCommand);
        } else {
            getLogger().severe("Failed to register 'rvc' command - is it missing from plugin.yml?");
        }

        getServer().getPluginManager().registerEvents(new GuiListener(this), this);

        DiamondItems.registerDiamondItems(this);
        DiamondArmour.registerDiamondArmour(this);
        IronItems.registerIronItems(this);
        IronArmour.registerIronArmour(this);
        StoneItems.registerStoneItems(this);
        WoodItems.registerWoodItems(this);
        LeatherArmour.registerLeatherArmour(this);
        NetherItems.registerNetherItems(this);
        NetherArmour.registerNetherArmour(this);
        Planks.registerPlankRecipes(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Reversible Crafting has been disabled!");
    }
}