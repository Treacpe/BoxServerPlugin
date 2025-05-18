package me.treacpe.boxServerPlugin.listeners;

import me.treacpe.boxServerPlugin.player.PlayerProgressManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        // Load player's progress
        PlayerProgressManager.loadProgress(player);

        // Give Box Menu item
        ItemStack menuItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = menuItem.getItemMeta();
        meta.setDisplayName("§b☰ Box Menu");
        menuItem.setItemMeta(meta);
        player.getInventory().setItem(8, menuItem);
    }
}
