package me.treacpe.boxServerPlugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class CreateItem {

    private final Material material;
    private String displayName;
    private List<String> lore = new ArrayList<>();
    private boolean unbreakable;
    private int customModelData = 0;
    private final Map<Enchantment, Integer> enchantments = new LinkedHashMap<>();
    private String category;            // Timber, Mineral, etc.
    private String categoryOrigin;      // "assigned" or "autodetected"
    private String type;                // "item" or "block"

    public CreateItem(Material material) {
        this.material = material;
    }
    // setters for chaining
    public CreateItem setDisplayName(String name) { this.displayName = name; return this; }
    public CreateItem setLore(List<String> lore) { this.lore = lore; return this; }
    public CreateItem setUnbreakable(boolean unbreakable) { this.unbreakable = unbreakable; return this; }
    public CreateItem setCustomModelData(int data) { this.customModelData = data; return this; }
    public CreateItem addEnchantment(Enchantment e, int level) { enchantments.put(e, level); return this; }
    public CreateItem setCategory(String category) { this.category = category; return this; }
    public CreateItem setCategoryOrigin(String origin) { this.categoryOrigin = origin; return this; }
    public CreateItem setType(String type) { this.type = type; return this; }

    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        if (displayName != null) meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        if (!lore.isEmpty()) {
            List<String> coloredLore = new ArrayList<>();
            for (String s : lore) coloredLore.add(ChatColor.translateAlternateColorCodes('&', s));
            meta.setLore(coloredLore);
        }
        meta.setUnbreakable(unbreakable);
        if (unbreakable) meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        if (customModelData != 0) meta.setCustomModelData(customModelData);
        for (Map.Entry<Enchantment, Integer> ent : enchantments.entrySet()) {
            meta.addEnchant(ent.getKey(), ent.getValue(), true);
        }
        item.setItemMeta(meta);
        return item;
    }

    // getters for serialization
    // ...
}
