package me.treacpe.boxServerPlugin.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.List;
import java.util.Map;

public class ItemData {

    public String id;                    // Unique item ID
    public Material material;            // Material type
    public String name;                  // Display name
    public List<String> lore;            // Lore lines
    public boolean unbreakable;          // Unbreakable flag
    public Map<Enchantment, Integer> enchants; // Enchantments
    public String category;              // Assigned category (user-selected)
    public String autoCategory;          // Auto-detected category
    public boolean isBlock;              // Is this a block?
    public int customModelData;          // Optional

    public ItemData(String id) {
        this.id = id;
    }
}
