package me.treacpe.boxServerPlugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateItem {

    private final Material material;
    private final String name;
    private final String collectionCategory;
    private final boolean unbreakable;

    public CreateItem(Material material, String name, String collectionCategory, boolean unbreakable) {
        this.material = material;
        this.name = name;
        this.collectionCategory = collectionCategory;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public String getCollectionCategory() {
        return collectionCategory;
    }

    public boolean isUnbreakable(){
        return unbreakable;
    }

    // Converts this CustomItem into an actual ItemStack you can give players
    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);

             // Apply unbreakable rule
            meta.setUnbreakable(unbreakable);

            if (unbreakable){
                meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            }
        }

        return item;
    }
}

