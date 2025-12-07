package me.treacpe.boxServerPlugin.commands;

import me.treacpe.boxServerPlugin.items.CreateItem;
import me.treacpe.boxServerPlugin.items.ItemRegistry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CiCommand implements CommandExecutor {

    private static final String PERMISSION = "boxplugin.ci";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (!player.hasPermission(PERMISSION)) {
            player.sendMessage("");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /ci <create|give|list>");
            return true;
        }

        String sub = args[0].toLowerCase();

        switch (sub) {

            case "create":
                return handleCreate(player, args);

            case "give":
                return handleGive(player, args);

            case "list":
                return handleList(player);

            default:
                player.sendMessage(ChatColor.RED + "Unknown subcommand.");
                return true;
        }
    }

    private boolean handleCreate(Player p, String[] args) {
        if (args.length < 5) {
            p.sendMessage(ChatColor.RED + "Usage: /ci create <id> <material> <name> <unbreakable>");
            return true;
        }

        String id = args[1];

        if (ItemRegistry.exists(id)) {
            p.sendMessage(ChatColor.RED + "Item ID already exists.");
            return true;
        }

        Material material = Material.getMaterial(args[2].toUpperCase());
        if (material == null) {
            p.sendMessage(ChatColor.RED + "Invalid material: " + args[2]);
            return true;
        }

        String name = ChatColor.translateAlternateColorCodes('&', args[3]);
        boolean unbreakable = Boolean.parseBoolean(args[4]);

        CreateItem item = new CreateItem(material, name, "Custom", unbreakable);

        ItemRegistry.register(id, item);

        p.sendMessage(ChatColor.GREEN + "Created custom item: " + id);
        return true;
    }


    private boolean handleGive(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(ChatColor.RED + "Usage: /ci give <itemID>");
            return true;
        }

        String id = args[1];
        CreateItem item = ItemRegistry.get(id);

        if (item == null) {
            p.sendMessage(ChatColor.RED + "Unknown item: " + id);
            return true;
        }

        p.getInventory().addItem(item.toItemStack());
        p.sendMessage(ChatColor.GREEN + "Given item: " + id);
        return true;
    }


    private boolean handleList(Player p) {
        p.sendMessage(ChatColor.YELLOW + "Registered custom items:");
        for (String key : ItemRegistry.getAll().keySet()) {
            p.sendMessage(ChatColor.GRAY + "- " + key);
        }
        return true;
    }
}
