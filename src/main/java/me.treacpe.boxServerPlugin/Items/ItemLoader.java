package me.treacpe.boxServerPlugin.items;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ItemLoader {

    private final JavaPlugin plugin;

    public ItemLoader(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public Map<String, ItemData> loadItems() {
        Map<String, ItemData> map = new HashMap<>();

        ConfigurationSection sec = plugin.getConfig().getConfigurationSection("items");
        if (sec == null) return map;

        for (String key : sec.getKeys(false)) {
            ConfigurationSection s = sec.getConfigurationSection(key);
            if (s == null) continue;

            ItemData data = new ItemData(key);

            data.material = Material.matchMaterial(s.getString("material", "STONE"));
            data.name = s.getString("name");
            data.lore = s.getStringList("lore");
            data.unbreakable = s.getBoolean("unbreakable", false);
            data.customModelData = s.getInt("custom-model-data", -1);

            // Category handling
            data.category = s.getString("category");
            data.autoCategory = s.getString("auto-detect");

            // Block detection
            data.isBlock = data.material.isBlock();

            // Enchants
            Map<Enchantment, Integer> enchMap = new HashMap<>();
            ConfigurationSection es = s.getConfigurationSection("enchants");
            if (es != null) {
                for (String en : es.getKeys(false)) {
                    Enchantment e = Enchantment.getByName(en);
                    if (e != null) enchMap.put(e, es.getInt(en));
                }
            }
            data.enchants = enchMap;

            map.put(key, data);
        }

        return map;
    }
}
