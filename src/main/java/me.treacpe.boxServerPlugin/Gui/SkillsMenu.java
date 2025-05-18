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

public class SkillsMenu {

    public static final String SKILLS_MENU_TITLE = "§aYour Skills";
    private static final int SIZE = 27;

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, SIZE, SKILLS_MENU_TITLE);

        inv.setItem(10, createSkillItem(Material.IRON_SWORD, "Combat", 5, 123, 250));
        inv.setItem(11, createSkillItem(Material.IRON_AXE, "Timber", 3, 89, 150));
        inv.setItem(12, createSkillItem(Material.IRON_PICKAXE, "Mineral", 4, 200, 400));
        inv.setItem(13, createSkillItem(Material.IRON_SHOVEL, "Excavator", 2, 40, 100));
        inv.setItem(14, createSkillItem(Material.IRON_HOE, "Agriculture", 6, 350, 600));
        inv.setItem(22, createCloseItem());

        player.openInventory(inv);
    }

    private static ItemStack createSkillItem(Material material, String name, int level, int currentXP, int xpToNext) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        String bar = getProgressBar((int) ((double) currentXP / xpToNext * 100));
        meta.setDisplayName("§e" + name + " §7(Level " + level + ")");
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
            if (i < bars) bar.append(ChatColor.GREEN + "|");
            else bar.append(ChatColor.DARK_GRAY + "|");
        }
        bar.append(ChatColor.GRAY + "] ").append(ChatColor.YELLOW).append(percent).append("%");
        return bar.toString();
    }

    public static void handleClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().equals(SKILLS_MENU_TITLE)) return;
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;

        if (clicked.getType() == Material.BARRIER) {
            player.closeInventory();
        }
    }
}
