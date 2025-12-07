package me.treacpe.boxServerPlugin.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class CustomItem {

    private final Material material;
    private final String name;
    private final String collectionCategory;
    private final boolean unbreakable;
    private final Map<Enchantment, Integer> enchantments = new HashMap<>(); // <-- NEW

    public CustomItem(Material material, String name, String collectionCategory, boolean unbreakable) {
        this.material = material;
        this.name = name;
        this.collectionCategory = collectionCategory;
        this.unbreakable = unbreakable;
    }

    // Add enchantment to this item
    public CustomItem addEnchantment(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this; // allows chaining
    }

    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            meta.setUnbreakable(unbreakable);
            if (unbreakable) {
                meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            }

            // Apply enchantments
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                meta.addEnchant(entry.getKey(), entry.getValue(), true); // `true` allows all levels
            }

            item.setItemMeta(meta);
        }

        return item;
    }
}
