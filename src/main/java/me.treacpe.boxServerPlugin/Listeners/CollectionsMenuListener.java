package me.treacpe.boxServerPlugin.listeners;

import me.treacpe.boxServerPlugin.gui.CollectionsMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CollectionsMenuListener implements Listener {

    @EventHandler
    public void onCollectionClick(InventoryClickEvent e) {
        CollectionsMenu.handleClick(e);
    }
}
