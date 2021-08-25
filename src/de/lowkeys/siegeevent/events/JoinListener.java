package de.lowkeys.siegeevent.events;

import de.lowkeys.siegeevent.main.SiegeEvent;
import de.lowkeys.siegeevent.utils.InventorySaver;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(SiegeEvent.isEventRunning()) {
            if(InventorySaver.getInventoryMap().get(event.getPlayer().getUniqueId()) == null) {
                InventorySaver.storeSpecificInventory(event.getPlayer().getUniqueId(), event.getPlayer().getInventory().getContents());
                event.getPlayer().getInventory().clear();
            } else {
                event.getPlayer().getInventory().clear();
            }
        }
    }
}
