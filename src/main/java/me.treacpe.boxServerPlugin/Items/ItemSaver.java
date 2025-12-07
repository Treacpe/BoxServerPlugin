package me.treacpe.boxServerPlugin.items;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemSaver {

    private final JavaPlugin plugin;

    public ItemSaver(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void saveItem(ItemData data) {
        ConfigurationSection sec = plugin.getConfig().createSection("items." + data.id);

        sec.set("material", data.material.toString());
        sec.set("name", data.name);
        sec.set("lore", data.lore);
        sec.set("unbreakable", data.unbreakable);
        sec.set("custom-model-data", data.customModelData);

        sec.set("category", data.category);
        sec.set("auto-detect", data.autoCategory);
        sec.set("is-block", data.isBlock);

        // Enchants
        ConfigurationSection enchSec = sec.createSection("enchants");
        if (data.enchants != null) {
            for (var e : data.enchants.entrySet()) {
                enchSec.set(e.getKey().getName(), e.getValue());
            }
        }

        plugin.saveConfig();
    }
}
