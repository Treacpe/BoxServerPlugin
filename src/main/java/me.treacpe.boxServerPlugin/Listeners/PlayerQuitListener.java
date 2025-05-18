package me.treacpe.boxServerPlugin.listeners;

import me.treacpe.boxServerPlugin.player.PlayerProgressManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerProgressManager.saveProgress(e.getPlayer());
    }
}
