package de.lowkeys.siegeevent.utils;

import de.lowkeys.siegeevent.main.SiegeEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

public class BlockSaver {
    private static ArrayList<Block> placedBlocks = new ArrayList<>();
    private static ArrayList<Block> brokenBlocks = new ArrayList<>();
    private static ArrayList<Material> brokenBlockMaterials = new ArrayList<>();
    private static HashMap<Block, Material> brokenBlocksMap = new HashMap<>();

    public static void restoreBlocks() {

        for(Block block : placedBlocks) {
            block.setType(Material.AIR);
        }
        for(Block block : brokenBlocksMap.keySet()) {
            block.setType(brokenBlocksMap.get(block));
        }

        placedBlocks.clear();
        brokenBlocks.clear();
    }

    public static void addPlacedBlock(Block block) {
        placedBlocks.add(block);
    }

    public static void addBrokenBlock(Block block) {
        brokenBlocks.add(block);
        brokenBlockMaterials.add(block.getType());

        brokenBlocksMap.put(block, block.getType());
    }

    public static ArrayList<Block> getPlacedBlocks() {
        return placedBlocks;
    }
}
