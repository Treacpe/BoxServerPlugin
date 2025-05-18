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

public class BoxMenu {

    public static final String MENU_TITLE = "§8☰ Box Menu";
    private static final int MENU_SIZE = 54; // 9 x 6

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, MENU_SIZE, MENU_TITLE);

        // Example menu item: Skills
        inv.setItem(10, createMenuItem(Material.IRON_SWORD, "§aSkills", "§7View your current skills", getProgressBar(35)));
        inv.setItem(12, createMenuItem(Material.BOOKSHELF, "§aCollections", "§7View your collections", getProgressBar(70)));
        inv.setItem(14, createMenuItem(Material.BOOK, "§cRecipe Book", "§c§lComing Soon"));
        inv.setItem(16, createMenuItem(Material.EXPERIENCE_BOTTLE, "§aLeveling", "§7Your XP Progress", getProgressBar(15)));
        inv.setItem(28, createMenuItem(Material.WRITABLE_BOOK, "§cQuest Log", "§c§lComing Soon"));
        inv.setItem(30, createMenuItem(Material.CLOCK, "§cCalendar and Events", "§c§lComing Soon"));
        inv.setItem(32, createMenuItem(Material.CHEST, "§aStorage", "§7Your ender-like vault"));
        inv.setItem(34, createMenuItem(Material.TOTEM_OF_UNDYING, "§aTalisman", "§7Your equipped talismans"));

        inv.setItem(37, createMenuItem(Material.NAME_TAG, "§cPets", "§c§lComing Soon"));
        inv.setItem(39, createMenuItem(Material.CRAFTING_TABLE, "§6Crafting Table", "§eUnlock with Booster Cookie"));
        inv.setItem(41, createMenuItem(Material.LEATHER_CHESTPLATE, "§6Wardrobe", "§eUnlock with Store Purchase"));
        inv.setItem(43, createMenuItem(Material.ENDER_CHEST, "§6Personal Bank", "§eUnlock with Store Purchase"));
        inv.setItem(46, createMenuItem(Material.ENDER_PEARL, "§cFast Travel", "§c§lComing Soon"));
        inv.setItem(48, createMenuItem(Material.PLAYER_HEAD, "§cProfile Management", "§c§lComing Soon"));
        inv.setItem(50, createMenuItem(Material.LEGACY_REDSTONE_COMPARATOR, "§cSettings", "§c§lComing Soon"));
        inv.setItem(52, createMenuItem(Material.CAKE, "§6Booster Cake", "§eUnlock to boost stats"));

        inv.setItem(49, createMenuItem(Material.BARRIER, "§cClose Menu", "§7Click to close"));

        player.openInventory(inv);
    }

    private static ItemStack createMenuItem(Material mat, String name, String... lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
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
        if (!e.getView().getTitle().equals(MENU_TITLE)) return;
        e.setCancelled(true); // prevent item movement

        Player player = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;

        String itemName = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());

        switch (itemName) {
            case "Close Menu":
                player.closeInventory();
                break;
            case "Skills":
                SkillsMenu.open(player);
                break;
            case "Collections":
                me.treacpe.boxServerPlugin.gui.CollectionGui.open(player);
                break;
            default:
                player.sendMessage("§cThat feature is coming soon or locked.");
        }
    }
}