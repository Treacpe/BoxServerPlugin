package me.treacpe.boxServerPlugin.items;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {

    public static Map<String, ItemData> ITEMS = new HashMap<>();

    public static void load(JavaPlugin plugin) {
        plugin.reloadConfig();
        ItemLoader loader = new ItemLoader(plugin);
        ITEMS = loader.loadItems();
    }

    public static void save(JavaPlugin plugin, ItemData data) {
        ItemSaver saver = new ItemSaver(plugin);
        saver.saveItem(data);
    }

    public static ItemData get(String id) {
        return ITEMS.get(id);
    }

    public static boolean exists(String id) {
        return ITEMS.containsKey(id);
    }
}
