package me.treacpe.boxServerPlugin.items;

import org.bukkit.Material;

import java.util.*;

public class CustomItemRegistry {

    private static final Map<Material, CustomItem> blockToCustomItem = new HashMap<>();
    private static final Set<Material> validTools = new HashSet<>();

    // Tools allowed to break specific collection categories
    private static final Map<Material, Set<String>> toolToAllowedCategories = new HashMap<>();

    public static void registerItem(CustomItem item) {
        blockToCustomItem.put(item.getMaterial(), item);
    }

    public static CustomItem getCustomItem(Material material) {
        return blockToCustomItem.get(material);
    }

    public static boolean isCustomBlock(Material material) {
        return blockToCustomItem.containsKey(material);
    }

    public static void registerToolCategory(Material tool, String collectionCategory) {
        toolToAllowedCategories.computeIfAbsent(tool, k -> new HashSet<>()).add(collectionCategory);
        validTools.add(tool);
    }

    public static boolean isValidTool(Material tool) {
        return validTools.contains(tool);
    }

    public static boolean canBreakCategory(Material tool, String category) {
        if (tool == Material.AIR || !toolToAllowedCategories.containsKey(tool)) return false;
        return toolToAllowedCategories.get(tool).contains(category);
    }

    public static void initDefaults() {
        // Example: Axes can break Timber Collection
        registerToolCategory(Material.WOODEN_AXE, "TIMBER");
        registerToolCategory(Material.STONE_AXE, "TIMBER");
        registerToolCategory(Material.IRON_AXE, "TIMBER");
        registerToolCategory(Material.DIAMOND_AXE, "TIMBER");
        registerToolCategory(Material.NETHERITE_AXE, "TIMBER");

        // Example: Pickaxes can break Mineral Collection
        registerToolCategory(Material.WOODEN_PICKAXE, "MINERAL");
        registerToolCategory(Material.IRON_PICKAXE, "MINERAL");
        registerToolCategory(Material.DIAMOND_PICKAXE, "MINERAL");
        registerToolCategory(Material.NETHERITE_PICKAXE, "MINERAL");

        // Shovels → Excavator
        registerToolCategory(Material.WOODEN_SHOVEL, "EXCAVATOR");
        registerToolCategory(Material.IRON_SHOVEL, "EXCAVATOR");
        registerToolCategory(Material.NETHERITE_SHOVEL, "EXCAVATOR");

        // Hoes → Agriculture
        registerToolCategory(Material.WOODEN_HOE, "AGRICULTURE");
        registerToolCategory(Material.IRON_HOE, "AGRICULTURE");
        registerToolCategory(Material.NETHERITE_HOE, "AGRICULTURE");

        // Swords → Combatant (can affect mobs, not blocks)
        registerToolCategory(Material.WOODEN_SWORD, "COMBATANT");
        registerToolCategory(Material.IRON_SWORD, "COMBATANT");
        registerToolCategory(Material.NETHERITE_SWORD, "COMBATANT");

        // Oak Wood
        registerItem(new CustomItem(Material.OAK_WOOD, "§6Oak Wood", "TIMBER", 1.0)); // 100% drop chance
        registerItem(new CustomItem(Material.OAK_LOG, "§6Oak Logs", "TIMBER", 1.0)); // 100% drop chance
        registerItem(new CustomItem(Material.OAK_PLANKS, "§6Oak Planks", "TIMBER", 1.0)); // 100% drop chance



    }
}
