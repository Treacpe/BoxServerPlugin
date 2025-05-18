package me.treacpe.boxServerPlugin.listeners;

import me.treacpe.boxServerPlugin.gui.BoxMenu;
import me.treacpe.boxServerPlugin.gui.SkillsGui;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SkillsGuiListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().equals(SkillsGui.MENU_TITLE)) return;
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;

        String itemName = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());

        if (itemName.equals("Back")) {
            BoxMenu.open(player);
        } else {
            player.sendMessage("§cThat skill has no details yet.");
        }
    }
}
