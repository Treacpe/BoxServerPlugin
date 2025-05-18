package me.treacpe.boxServerPlugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CollectionsMenu {

    public static final String COLLECTIONS_MENU_TITLE = "§6Your Collections";
    private static final int SIZE = 27;

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, SIZE, COLLECTIONS_MENU_TITLE);

        inv.setItem(10, createCollectionItem(Material.OAK_LOG, "Timber Collection", 4, 240, 500));
        inv.setItem(11, createCollectionItem(Material.STONE_BRICKS, "Mineral Collection", 2, 150, 300));
        inv.setItem(12, createCollectionItem(Material.ROTTEN_FLESH, "Combatant Collection", 3, 90, 200));
        inv.setItem(13, createCollectionItem(Material.GRASS_BLOCK, "Excavator Collection", 5, 450, 800));
        inv.setItem(14, createCollectionItem(Material.HAY_BLOCK, "Agriculture Collection", 1, 60, 150));
        inv.setItem(22, createCloseItem());

        player.openInventory(inv);
    }

    private static ItemStack createCollectionItem(Material material, String name, int tier, int currentXP, int xpToNext) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        String bar = getProgressBar((int) ((double) currentXP / xpToNext * 100));
        meta.setDisplayName("§e" + name + " §7(Tier " + tier + ")");
        meta.setLore(Arrays.asList(
                "§7Progress: " + bar,
                "§7" + currentXP + " / " + xpToNext + " XP"
        ));

        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack createCloseItem() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cClose Menu");
        item.setItemMeta(meta);
        return item;
    }

    private static String getProgressBar(int percent) {
        int bars = percent / 10;
        StringBuilder bar = new StringBuilder(ChatColor.GRAY + "[");
        for (int i = 0; i < 10; i++) {
            if (i < bars) bar.append(ChatColor.GOLD + "|");
            else bar.append(ChatColor.DARK_GRAY + "|");
        }
        bar.append(ChatColor.GRAY + "] ").append(ChatColor.YELLOW).append(percent).append("%");
        return bar.toString();
    }

    public static void handleClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().equals(COLLECTIONS_MENU_TITLE)) return;
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;

        if (clicked.getType() == Material.BARRIER) {
            player.closeInventory();
        }

        // Later: open individual collection details
    }
}
