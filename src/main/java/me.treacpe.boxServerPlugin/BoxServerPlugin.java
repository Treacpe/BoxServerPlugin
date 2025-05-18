package me.treacpe.boxServerPlugin;

import me.treacpe.boxServerPlugin.commands.BoxMenuCommand;
import me.treacpe.boxServerPlugin.items.CustomItemRegistry;
import me.treacpe.boxServerPlugin.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;
import me.treacpe.boxServerPlugin.listeners.QuitListener;

public class BoxServerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("✅ Box Server Plugin has been enabled!");

        // Register default tools and categories
        CustomItemRegistry.initDefaults();

        // Register Listener
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new SkillsMenuListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new CollectionsMenuListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakXPListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakXPListener(this), this);
        getServer().getPluginManager().registerEvents(new MobKillXPListener(), this);
        getServer().getPluginManager().registerEvents(new CollectionGuiListener(), this);
        getServer().getPluginManager().registerEvents(new MainMenuListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);


        // Register Commands
        getCommand("boxmenu").setExecutor(new BoxMenuCommand());

    }

    @Override
    public void onDisable() {
        getLogger().info("❌ Box Server Plugin has been disabled.");
    }
}
