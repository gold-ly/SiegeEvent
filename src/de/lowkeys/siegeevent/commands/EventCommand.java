package de.lowkeys.siegeevent.commands;

import de.lowkeys.siegeevent.main.SiegeEvent;
import de.lowkeys.siegeevent.utils.BlockSaver;
import de.lowkeys.siegeevent.utils.InventorySaver;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class EventCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            System.out.println("You have to be a player to execute this command!");
            return false;
        }
        Player player = (Player) commandSender;

        if(!player.hasPermission("event.admin")) {
            player.sendMessage("§cYou don't have permissions to run this command!");
            return false;
        }

        if(strings.length == 0) {
            player.sendMessage("§7 - §f/event start §7| Starts the event");
            player.sendMessage("§7 - §f/event stop §7| Stops the event");
            player.sendMessage("§7 - §f/event restore §7| Restores all blocks placed & broken within the event");
        }


        if(strings.length == 1) {
            switch (strings[0].toLowerCase(Locale.ROOT)) {
                case "start":
                    if(!SiegeEvent.isEventRunning()) {
                        SiegeEvent.toggleEventRunning();
                        InventorySaver.storeAllInventorys();
                        InventorySaver.clearAllInventorys();
                        player.sendMessage("§aGame started!");
                    } else {
                        player.sendMessage("§cGame is already running!");
                    }
                    break;
                case "stop":
                    if(SiegeEvent.isEventRunning()) {
                        SiegeEvent.toggleEventRunning();
                        InventorySaver.restoreAllInventorys();
                        player.sendMessage("§aGame stopped!");
                    } else {
                        player.sendMessage("§cGame is not running!");
                    }
                    break;
                case "restore":
                    BlockSaver.restoreBlocks();
                    player.sendMessage("§aRestored blocks!");
                    break;
                default:
                    player.sendMessage("§cThis is not a valid option. Run /event for help!");
            }
        }
        return false;
    }
}
