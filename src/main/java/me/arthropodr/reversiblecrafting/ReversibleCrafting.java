package me.arthropodr.reversiblecrafting;

import me.arthropodr.reversiblecrafting.commands.RvcGuiCommand;
import me.arthropodr.reversiblecrafting.listeners.GuiListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ReversibleCrafting extends JavaPlugin {
    private static ReversibleCrafting instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        if (!new File(getDataFolder(), "disabled-items.yml").exists()) {
            saveResource("disabled-items.yml", false);
        }

        getLogger().info("Reversible Crafting has been enabled!");

        RvcGuiCommand rvcGuiCommand = new RvcGuiCommand(this);

        PluginCommand command = getCommand("rvc");
        if (command != null) {
            command.setExecutor(rvcGuiCommand);
            command.setTabCompleter(rvcGuiCommand);
        } else {
            getLogger().severe("Failed to register 'rvc' command.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(new GuiListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Reversible Crafting has been disabled!");
        instance = null;
    }

    public static ReversibleCrafting getInstance() {
        return instance;
    }
}