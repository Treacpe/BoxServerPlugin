package me.treacpe.boxServerPlugin.listeners;

import me.treacpe.boxServerPlugin.items.CustomItem;
import me.treacpe.boxServerPlugin.items.CustomItemRegistry;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockBreakListener implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = block.getType();
        ItemStack tool = player.getInventory().getItemInMainHand();

        if (!CustomItemRegistry.isCustomBlock(blockType)) return;

        event.setDropItems(false); // Cancel vanilla drop
        event.setExpToDrop(0); // Optional: remove XP drop

        CustomItem customItem = CustomItemRegistry.getCustomItem(blockType);

        Material toolType = tool.getType();
        boolean isHand = toolType == Material.AIR || !CustomItemRegistry.isValidTool(toolType);

        // Allow breaking only with proper tool or fist/non-tool
        if (!isHand && !CustomItemRegistry.canBreakCategory(toolType, customItem.getCollectionCategory())) {
            return;
        }

        // Drop logic with percentage
        if (random.nextDouble() <= customItem.getDropChance()) {
            ItemStack drop = customItem.getDrop();
            block.getWorld().dropItemNaturally(block.getLocation(), drop);
        }
    }
}
