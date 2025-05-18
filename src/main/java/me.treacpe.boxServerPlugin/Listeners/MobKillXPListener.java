package me.treacpe.boxServerPlugin.listeners;

import me.treacpe.boxServerPlugin.player.PlayerProgressManager;
import me.treacpe.boxServerPlugin.player.PlayerProgressManager.CollectionType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MobKillXPListener implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Zombie zombie)) return;
        if (!(zombie.getKiller() instanceof Player player)) return;

        ItemStack tool = player.getInventory().getItemInMainHand();

        if (isCorrectTool(CollectionType.COMBATANT, tool) || isEmptyHandOrBlock(tool)) {
            event.getDrops().clear(); // Remove vanilla drops

            // Drop custom item with chance
            if (random.nextDouble() < 0.7) { // 70% drop chance
                event.getEntity().getWorld().dropItemNaturally(
                        event.getEntity().getLocation(),
                        new ItemStack(Material.ROTTEN_FLESH) // Replace with custom item later
                );
            }

            // Award XP
            PlayerProgressManager.addXp(player.getUniqueId(), CollectionType.COMBATANT, 10);
        } else {
            event.getDrops().clear(); // Wrong tool, no drops
        }
    }

    private boolean isCorrectTool(CollectionType type, ItemStack tool) {
        if (tool == null) return false;
        Material mat = tool.getType();
        return type == CollectionType.COMBATANT && mat.toString().contains("SWORD");
    }

    private boolean isEmptyHandOrBlock(ItemStack tool) {
        return tool == null || tool.getType().isAir() || tool.getType().isBlock();
    }
}
