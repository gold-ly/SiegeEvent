package de.lowkeys.siegeevent.events;

import de.lowkeys.siegeevent.main.SiegeEvent;
import de.lowkeys.siegeevent.utils.BlockSaver;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(!SiegeEvent.getStringifiedSelectedWorld().equalsIgnoreCase(event.getPlayer().getWorld().getName())) {
            return;
        }
        if(!SiegeEvent.isEventRunning()) {
            return;
        }

        if(!SiegeEvent.getAllowedBrokenMaterials().contains(event.getBlock().getType())) {
            event.setCancelled(true);
            return;
        }

        if(BlockSaver.getPlacedBlocks().contains(event.getBlock())) {
            System.out.println("Broken block is a block placed within the event!");
            return;
        }

        BlockSaver.addBrokenBlock(event.getBlock());
    }
}
