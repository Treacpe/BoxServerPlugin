package me.treacpe.boxServerPlugin.listeners;

import me.treacpe.boxServerPlugin.gui.BoxMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MainMenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(BoxMenu.MENU_TITLE)) {
            BoxMenu.handleClick(event);
        }
    }
}
