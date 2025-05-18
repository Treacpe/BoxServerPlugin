package me.treacpe.boxServerPlugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SkillsGui {

    public static final String MENU_TITLE = "§8★ Skills";

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, MENU_TITLE);

        // Example skills (you can expand this later)
        inv.setItem(10, createSkillItem(Material.IRON_PICKAXE, "§aMining", 23));
        inv.setItem(12, createSkillItem(Material.IRON_AXE, "§aTimber", 41));
        inv.setItem(14, createSkillItem(Material.IRON_SHOVEL, "§aExcavation", 67));
        inv.setItem(16, createSkillItem(Material.IRON_HOE, "§aAgriculture", 89));
        inv.setItem(28, createSkillItem(Material.IRON_SWORD, "§aCombat", 12));

        // Close button
        inv.setItem(49, createSkillItem(Material.BARRIER, "§cBack", "§7Return to Box Menu"));
        player.openInventory(inv);
    }
    private static ItemStack createSkillItem(Material mat, String name, String... lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack createSkillItem(Material mat, String name, int progress) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList("§7Progress", getProgressBar(progress), "§7Level: " + (progress / 10)));
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
}
