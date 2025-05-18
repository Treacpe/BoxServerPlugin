package me.treacpe.boxServerPlugin.listeners;

import me.treacpe.boxServerPlugin.player.PlayerProgressManager;
import me.treacpe.boxServerPlugin.player.PlayerProgressManager.CollectionType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class BlockBreakXPListener implements Listener {

    private final Plugin plugin;

    public BlockBreakXPListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack tool = player.getInventory().getItemInMainHand();
        Material type = block.getType();

        // Check and apply XP + drop rules
        if (isTimberBlock(type)) {
            handleBlock(event, player, block, tool, CollectionType.TIMBER, Material.OAK_LOG, 5);
        } else if (isMineralBlock(type)) {
            handleBlock(event, player, block, tool, CollectionType.MINERAL, Material.STONE, 5);
        } else if (isExcavatorBlock(type)) {
            handleBlock(event, player, block, tool, CollectionType.EXCAVATOR, Material.DIRT, 3);
        } else if (isAgricultureBlock(type)) {
            handleBlock(event, player, block, tool, CollectionType.AGRICULTURE, Material.HAY_BLOCK, 3);
        }
    }

    private void handleBlock(BlockBreakEvent event, Player player, Block block, ItemStack tool,
                             CollectionType type, Material customDrop, int xp) {
        if (isCorrectTool(type, tool) || isEmptyHandOrBlock(tool)) {
            event.setDropItems(false);
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(customDrop));
            PlayerProgressManager.addXp(player.getUniqueId(), type, xp);
        } else {
            // Cancel drop if wrong tool
            event.setDropItems(false);
        }
    }

    private boolean isCorrectTool(CollectionType type, ItemStack tool) {
        if (tool == null) return false;
        Material mat = tool.getType();
        return switch (type) {
            case TIMBER -> mat.toString().contains("AXE");
            case MINERAL -> mat.toString().contains("PICKAXE");
            case COMBATANT -> mat.toString().contains("SWORD");
            case EXCAVATOR -> mat.toString().contains("SHOVEL");
            case AGRICULTURE -> mat.toString().contains("HOE");
        };
    }

    private boolean isEmptyHandOrBlock(ItemStack tool) {
        return tool == null || tool.getType().isAir() || tool.getType().isBlock();
    }

    private boolean isTimberBlock(Material mat) {
        return mat == Material.OAK_LOG || mat == Material.OAK_WOOD;
    }

    private boolean isMineralBlock(Material mat) {
        return mat == Material.STONE || mat == Material.COBBLESTONE || mat == Material.STONE_BRICKS;
    }

    private boolean isExcavatorBlock(Material mat) {
        return mat == Material.DIRT || mat == Material.GRASS_BLOCK || mat == Material.ROOTED_DIRT;
    }

    private boolean isAgricultureBlock(Material mat) {
        return mat == Material.WHEAT || mat == Material.HAY_BLOCK || mat == Material.WHEAT_SEEDS;
    }
}
