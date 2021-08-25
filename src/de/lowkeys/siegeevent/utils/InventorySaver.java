package de.lowkeys.siegeevent.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class InventorySaver {
    private static HashMap<UUID, ItemStack[]> inventoryMap = new HashMap<>();

    public static void storeAllInventorys() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            inventoryMap.put(player.getUniqueId(), player.getInventory().getContents());
        }
    }

    public static void storeSpecificInventory(UUID uuid, ItemStack[] content) {
        inventoryMap.put(uuid, content);
    }

    public static void restoreAllInventorys() {
        for(UUID uuid : inventoryMap.keySet()) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
            if(offlinePlayer.isOnline()) {
                Player player = offlinePlayer.getPlayer();
                player.getInventory().setContents(inventoryMap.get(uuid));
            }
        }

        inventoryMap.clear();
    }

    public static void clearAllInventorys() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().clear();
        }
    }

    public static void restoreSpecificInventory(UUID uuid) {
        if(Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Bukkit.getPlayer(uuid).getInventory().setContents(inventoryMap.get(uuid));
        }
    }

    public static HashMap<UUID, ItemStack[]> getInventoryMap() {
        return inventoryMap;
    }
}
