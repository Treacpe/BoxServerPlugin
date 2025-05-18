package me.treacpe.boxServerPlugin.gui;

import me.treacpe.boxServerPlugin.player.PlayerProgressManager;
import me.treacpe.boxServerPlugin.player.PlayerProgressManager.CollectionType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public class CollectionGui {

    public static void open(Player player) {
        Inventory gui = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "Your Collections");

        UUID uuid = player.getUniqueId();

        gui.setItem(10, getCollectionIcon(Material.OAK_LOG, "Timber Collection", uuid, CollectionType.TIMBER));
        gui.setItem(12, getCollectionIcon(Material.STONE, "Mineral Collection", uuid, CollectionType.MINERAL));
        gui.setItem(14, getCollectionIcon(Material.ROTTEN_FLESH, "Combatant Collection", uuid, CollectionType.COMBATANT));
        gui.setItem(16, getCollectionIcon(Material.GRASS_BLOCK, "Excavator Collection", uuid, CollectionType.EXCAVATOR));
        gui.setItem(28, getCollectionIcon(Material.WHEAT, "Agriculture Collection", uuid, CollectionType.AGRICULTURE));

        player.openInventory(gui);
    }

    private static ItemStack getCollectionIcon(Material material, String name, UUID uuid, CollectionType type) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        int xp = PlayerProgressManager.getXp(uuid, type);
        int level = PlayerProgressManager.getLevelFromXp(xp);
        int next = PlayerProgressManager.getXpForLevel(level + 1);
        int prev = PlayerProgressManager.getXpForLevel(level);
        int progress = xp - prev;
        int required = next - prev;

        double percent = (required == 0) ? 1.0 : ((double) progress / required);
        String progressBar = generateProgressBar(percent);

        meta.setDisplayName(ChatColor.GOLD + name);
        meta.setLore(List.of(
                ChatColor.YELLOW + "Level: " + ChatColor.GREEN + level,
                ChatColor.YELLOW + "XP: " + ChatColor.AQUA + progress + " / " + required,
                ChatColor.YELLOW + "Progress: " + ChatColor.GREEN + progressBar
        ));
        item.setItemMeta(meta);
        return item;
    }

    private static String generateProgressBar(double percent) {
        int totalBars = 20;
        int filledBars = (int) Math.round(percent * totalBars);
        StringBuilder bar = new StringBuilder();

        bar.append(ChatColor.DARK_GREEN).append("[");

        for (int i = 0; i < totalBars; i++) {
            if (i < filledBars) {
                bar.append(ChatColor.GREEN).append("|");
            } else {
                bar.append(ChatColor.GRAY).append("|");
            }
        }

        bar.append(ChatColor.DARK_GREEN).append("]");
        return bar.toString();
    }
}
