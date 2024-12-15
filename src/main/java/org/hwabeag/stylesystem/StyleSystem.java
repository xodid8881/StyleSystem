package org.hwabeag.stylesystem;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.hwabeag.stylesystem.commands.*;
import org.hwabeag.stylesystem.config.ConfigManager;
import org.hwabeag.stylesystem.events.BookClickEvent;
import org.hwabeag.stylesystem.events.InvClickEvent;
import org.hwabeag.stylesystem.events.LoginEvent;
import org.hwabeag.stylesystem.expansions.StyleExpansion;

import java.util.Objects;

public final class StyleSystem extends JavaPlugin {

    private static ConfigManager configManager;

    private FileConfiguration config;

    public static StyleSystem getPlugin() {
        return JavaPlugin.getPlugin(StyleSystem.class);
    }

    public static void getConfigManager() {
        if (configManager == null)
            configManager = new ConfigManager();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new BookClickEvent(), this);
        getServer().getPluginManager().registerEvents(new InvClickEvent(), this);
        getServer().getPluginManager().registerEvents(new LoginEvent(), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(getServer().getPluginCommand("칭호생성")).setExecutor(new CreateCommand());
        Objects.requireNonNull(getServer().getPluginCommand("칭호제거")).setExecutor(new DeleteCommand());
        Objects.requireNonNull(getServer().getPluginCommand("칭호지급")).setExecutor(new GiveCommand());
        Objects.requireNonNull(getServer().getPluginCommand("칭호리스트")).setExecutor(new ListCommand());
        Objects.requireNonNull(getServer().getPluginCommand("칭호관리")).setExecutor(new SettingCommand());
        Objects.requireNonNull(getServer().getPluginCommand("한닉관리")).setExecutor(new NickChangeCommand());
        Objects.requireNonNull(getServer().getPluginCommand("한닉권")).setExecutor(new NickChangeGiveCommand());
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("[StyleSystem] Enable");
        getConfigManager();
        registerCommands();
        registerEvents();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new StyleExpansion(this).register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("[StyleSystem] Disable");
        ConfigManager.saveConfigs();
    }
}
