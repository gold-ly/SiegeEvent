package de.lowkeys.siegeevent.main;

import de.lowkeys.siegeevent.commands.EventCommand;
import de.lowkeys.siegeevent.events.BlockBreakListener;
import de.lowkeys.siegeevent.events.BlockPlaceListener;
import de.lowkeys.siegeevent.events.JoinListener;
import de.lowkeys.siegeevent.events.QuitListener;
import de.lowkeys.siegeevent.utils.BlockSaver;
import de.lowkeys.siegeevent.utils.InventorySaver;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class SiegeEvent extends JavaPlugin {
    private static SiegeEvent INSTANCE;
    private static boolean eventRunning;
    private static ArrayList<Material> allowedPlacedMaterials = new ArrayList<>();
    private static ArrayList<Material> allowedBrokenMaterials = new ArrayList<>();
    private static String stringifiedSelectedWorld;

    @Override
    public void onEnable() {
        INSTANCE = this;

        addDefaultConfig();
        loadConfig();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);

        this.getCommand("event").setExecutor(new EventCommand());
    }

    @Override
    public void onDisable() {
        BlockSaver.restoreBlocks();
        InventorySaver.restoreAllInventorys();
    }

    private void addDefaultConfig() {
        INSTANCE.getConfig().addDefault("selectedWorld", "world");
        INSTANCE.getConfig().addDefault("allowedPlacedBlocks", new ArrayList<String>());
        INSTANCE.getConfig().addDefault("allowedBrokenBlocks", new ArrayList<String>());
        INSTANCE.getConfig().options().copyDefaults(true);
        INSTANCE.saveConfig();
    }

    private void loadConfig() {
        ArrayList<String> stringifiedAllowedPlacedMaterials = (ArrayList<String>) getConfig().getList("allowedPlacedBlocks");
        ArrayList<String> stringifiedAllowedBrokenMaterials = (ArrayList<String>) getConfig().getList("allowedBrokenBlocks");
        stringifiedSelectedWorld = getConfig().getString("selectedWorld");

        for(String materialName : stringifiedAllowedPlacedMaterials) {
            allowedPlacedMaterials.add(Material.getMaterial(materialName));
        }

        for(String materialName : stringifiedAllowedBrokenMaterials) {
            allowedBrokenMaterials.add(Material.getMaterial(materialName));
        }
    }

    public static ArrayList<Material> getAllowedPlacedMaterials() {
        return allowedPlacedMaterials;
    }
    public static ArrayList<Material> getAllowedBrokenMaterials() {
        return allowedBrokenMaterials;
    }
    public static String getStringifiedSelectedWorld() {
        return stringifiedSelectedWorld;
    }
    public static boolean isEventRunning() {
        return eventRunning;
    }
    public static void toggleEventRunning() {
        eventRunning = !eventRunning;
    }
}
