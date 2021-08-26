package de.lowkeys.siegeevent.events;

import de.lowkeys.siegeevent.main.SiegeEvent;
import de.lowkeys.siegeevent.utils.InventorySaver;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if(SiegeEvent.isEventRunning()) {
            // When a player leaves while the event is running, his inventory is recovered
            InventorySaver.restoreSpecificInventory(event.getPlayer().getUniqueId());
        }
    }
}
