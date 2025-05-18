package me.treacpe.boxServerPlugin.player;

import me.treacpe.boxServerPlugin.utils.DataStorage;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerProgressManager {

    public enum CollectionType {
        TIMBER, MINERAL, COMBATANT, EXCAVATOR, AGRICULTURE
    }

    // Inner class to track XP and Tier
    public static class CollectionProgress {
        private int xp = 0;
        private int tier = 0;

        public int getXp() {
            return xp;
        }

        public int getTier() {
            return tier;
        }

        public void addXp(int amount) {
            xp += amount;
            while (xp >= getXpRequiredForNextTier() && tier < 6) {
                xp -= getXpRequiredForNextTier();
                tier++;
            }
        }

        private int getXpRequiredForNextTier() {
            return 100 + (tier * 50);
        }

        public void setXp(int xp) {
            this.xp = xp;
        }

        public void setTier(int tier) {
            this.tier = tier;
        }
    }

    // In-memory storage
    private static final Map<UUID, Map<CollectionType, CollectionProgress>> playerData = new ConcurrentHashMap<>();

    // Load player data from file
    public static void loadProgress(Player player) {
        UUID uuid = player.getUniqueId();
        Map<CollectionType, CollectionProgress> progressMap = new EnumMap<>(CollectionType.class);

        for (CollectionType type : CollectionType.values()) {
            int xp = DataStorage.getInt(uuid, type.name() + "_XP");
            int tier = DataStorage.getInt(uuid, type.name() + "_Tier");

            CollectionProgress progress = new CollectionProgress();
            progress.setXp(xp);
            progress.setTier(tier);
            progressMap.put(type, progress);
        }

        playerData.put(uuid, progressMap);
    }

    // Save player data to file
    public static void saveProgress(Player player) {
        UUID uuid = player.getUniqueId();
        Map<CollectionType, CollectionProgress> progressMap = playerData.getOrDefault(uuid, Collections.emptyMap());

        for (Map.Entry<CollectionType, CollectionProgress> entry : progressMap.entrySet()) {
            CollectionType type = entry.getKey();
            CollectionProgress progress = entry.getValue();
            DataStorage.setInt(uuid, type.name() + "_XP", progress.getXp());
            DataStorage.setInt(uuid, type.name() + "_Tier", progress.getTier());
        }

        DataStorage.save(uuid);
    }

    // Public API
    public static void addXp(UUID playerId, CollectionType type, int amount) {
        Map<CollectionType, CollectionProgress> progressMap = playerData.computeIfAbsent(playerId, k -> new EnumMap<>(CollectionType.class));
        CollectionProgress progress = progressMap.computeIfAbsent(type, k -> new CollectionProgress());
        progress.addXp(amount);
    }

    public static int getXp(UUID playerId, CollectionType type) {
        return playerData.getOrDefault(playerId, Collections.emptyMap())
                .getOrDefault(type, new CollectionProgress()).getXp();
    }

    public static int getTier(UUID playerId, CollectionType type) {
        return playerData.getOrDefault(playerId, Collections.emptyMap())
                .getOrDefault(type, new CollectionProgress()).getTier();
    }

    public static Map<CollectionType, CollectionProgress> getAllProgress(UUID playerId) {
        return playerData.getOrDefault(playerId, new EnumMap<>(CollectionType.class));
    }
}
