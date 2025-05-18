package me.treacpe.boxServerPlugin.listeners;

import me.treacpe.boxServerPlugin.gui.SkillsMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SkillsMenuListener implements Listener {

    @EventHandler
    public void onSkillClick(InventoryClickEvent e) {
        SkillsMenu.handleClick(e);
    }
}
