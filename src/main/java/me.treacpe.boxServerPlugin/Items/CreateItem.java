package me.treacpe.boxServerPlugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateItem {

    private final ItemData data;

    public CreateItem(ItemData data) {
        this.data = data;
    }

    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(data.material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (data.name != null)
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', data.name));

            if (data.lore != null && !data.lore.isEmpty())
                meta.setLore(data.lore.stream()
                        .map(l -> ChatColor.translateAlternateColorCodes('&', l))
                        .toList());

            meta.setUnbreakable(data.unbreakable);
            if (data.unbreakable) meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

            if (data.customModelData > 0)
                meta.setCustomModelData(data.customModelData);

            if (data.enchants != null) {
                for (var e : data.enchants.entrySet()) {
                    meta.addEnchant(e.getKey(), e.getValue(), true);
                }
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    public ItemData getData() {
        return data;
    }
}
