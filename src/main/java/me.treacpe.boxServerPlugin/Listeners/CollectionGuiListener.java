package me.treacpe.boxServerPlugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CollectionGuiListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.DARK_GREEN + "Your Collections")) {
            event.setCancelled(true); // prevent taking items

            if (event.getCurrentItem() == null) return;
            Player player = (Player) event.getWhoClicked();

            player.sendMessage(ChatColor.GRAY + "This collection is coming soon.");
        }
    }
}
