package de.lowkeys.siegeevent.events;

import de.lowkeys.siegeevent.main.SiegeEvent;
import de.lowkeys.siegeevent.utils.BlockSaver;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(!SiegeEvent.getStringifiedSelectedWorld().equalsIgnoreCase(event.getPlayer().getWorld().getName())) {
            return;
        }
        if(!SiegeEvent.isEventRunning()) {
            return;
        }

        if(!SiegeEvent.getAllowedPlacedMaterials().contains(event.getBlockPlaced().getType())) {
            event.setCancelled(true);
            return;
        }

        BlockSaver.addPlacedBlock(event.getBlockPlaced());
    }
}
