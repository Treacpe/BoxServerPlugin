package me.treacpe.boxServerPlugin.items;

import org.bukkit.Material;
import org.bukkit.Enchantment;
import org.bukkit.ChatColor;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class ItemRegistry {

    private static final Map<String, CreateItem> registry = new HashMap<>();

    public static void register(String id, CreateItem item) {
        registry.put(id, item);
    }

    public static CreateItem get(String id) {
        return registry.get(id);
    }

    public static boolean exists(String id) {
        return registry.containsKey(id);
    }

    public static Map<String, CreateItem> getAll() {
        return registry;
    }

    public static final CreateItem 
        Tier1_Wooden_Pickaxe = new CreateItem(
            Material.WOODEN_PICKAXE,
            "&FTier 1&7 Wooden Pickaxe",
            "Mineral",
            true);
    public static final CreateItem 
        Tier2_Wooden_Pickaxe = new CreateItem(
            Material.WOODEN_PICKAXE,
            "&FTier 2&7 Wooden Pickaxe",
            "Mineral",
            true).addEnchantment(Enchantment.EFFICIENCY, 1);
}
