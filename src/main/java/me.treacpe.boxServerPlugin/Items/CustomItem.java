package me.treacpe.boxServerPlugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem {
    private final Material material;
    private final String name;
    private final String collectionCategory;
    private final double dropChance;

    public CustomItem(Material material, String name, String collectionCategory, double dropChance) {
        this.material = material;
        this.name = name;
        this.collectionCategory = collectionCategory;
        this.dropChance = dropChance;
    }

    public Material getMaterial() {
        return material;
    }

    public String getCollectionCategory() {
        return collectionCategory;
    }

    public double getDropChance() {
        return dropChance;
    }

    public ItemStack getDrop() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }
}
