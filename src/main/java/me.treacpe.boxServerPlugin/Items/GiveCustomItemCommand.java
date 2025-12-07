package me.treacpe.boxServerPlugin.commands;

import me.treacpe.boxServerPlugin.items.CreateItem;
import me.treacpe.boxServerPlugin.items.ItemRegistry;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class GiveCustomItemCommand implements CommandExecutor {

    private static final String PERMISSION = "boxplugin.createitem";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Only players can use this command
        if (!(sender instanceof Player)) {
            return true; // Silent fail
        }

        Player player = (Player) sender;

        // Permission check (silent if missing)
        if (!player.hasPermission(PERMISSION)) {
            player.sendMessage(""); // Blank message to hide command existence
            return true;
        }

        // Must specify itemID
        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /" + label + " <itemID>");
            return true;
        }

        String itemID = args[0];

        try {
            // Find the registry field by ID
            Field field = ItemRegistry.class.getField(itemID);

            Object obj = field.get(null);
            if (!(obj instanceof CreateItem)) {
                player.sendMessage(ChatColor.RED + "Invalid custom item: " + itemID);
                return true;
            }

            // Give item
            CreateItem custom = (CreateItem) obj;
            player.getInventory().addItem(custom.toItemStack());

            player.sendMessage(ChatColor.GREEN + "Given custom item: " + itemID);

        } catch (NoSuchFieldException e) {
            player.sendMessage(ChatColor.RED + "Unknown item: " + itemID);
        } catch (IllegalAccessException e) {
            player.sendMessage(ChatColor.RED + "Unable to load item: " + itemID);
        }

        return true;
    }
}
