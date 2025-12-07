package me.treacpe.boxServerPlugin.items;

import org.bukkit.Material;

import java.util.*;

public class CustomItemRegistry {

    public static final CustomItem 
        Tier1_Wooden_Pickaxe = new CustomItem(
            Material.WOODEN_PICKAXE,
            "&FTier 1&7 Wooden Pickaxe",
            "Mineral",
            True);
    public static final CustomItem 
        Tier2_Wooden_Pickaxe = new CustomItem(
            Material.WOODEN_PICKAXE,
            "&FTier 2&7 Wooden Pickaxe",
            "Mineral",
            True).addEnchantment(Enchantment.EFFICIENCY, 1);
}
