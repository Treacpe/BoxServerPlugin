package me.treacpe.boxServerPlugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class DataStorage {

    private static final HashMap<UUID, FileConfiguration> configs = new HashMap<>();
    private static final HashMap<UUID, File> files = new HashMap<>();

    public static void setup(UUID uuid) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("BoxServerPlugin").getDataFolder(), uuid + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        configs.put(uuid, config);
        files.put(uuid, file);
    }

    public static int getInt(UUID uuid, String path) {
        setup(uuid);
        return configs.get(uuid).getInt(path);
    }

    public static void setInt(UUID uuid, String path, int value) {
        setup(uuid);
        configs.get(uuid).set(path, value);
    }

    public static void save(UUID uuid) {
        try {
            configs.get(uuid).save(files.get(uuid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
