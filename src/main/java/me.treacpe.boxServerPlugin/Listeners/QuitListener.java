package me.treacpe.boxServerPlugin.listeners;

import me.treacpe.boxServerPlugin.player.PlayerProgressManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        PlayerProgressManager.saveProgress(player);
    }
}
