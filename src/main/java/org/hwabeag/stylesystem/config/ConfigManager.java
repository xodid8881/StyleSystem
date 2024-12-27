package org.hwabeag.stylesystem.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.hwabeag.stylesystem.StyleSystem;

import java.util.HashMap;

public class ConfigManager {
    private static final StyleSystem plugin = StyleSystem.getPlugin();

    private static final HashMap<String, ConfigMaker> configSet = new HashMap<>();

    public ConfigManager() {
        String path = plugin.getDataFolder().getAbsolutePath();
        configSet.put("stylesystem", new ConfigMaker(path, "StyleSystem.yml"));
        configSet.put("player", new ConfigMaker(path, "Player.yml"));
        loadSettings();
        saveConfigs();
    }

    public static void reloadConfigs() {
        for (String key : configSet.keySet()){
            plugin.getLogger().info(key);
            configSet.get(key).reloadConfig();
        }
    }

    public static void saveConfigs(){
        for (String key : configSet.keySet())
            configSet.get(key).saveConfig();
    }

    public static FileConfiguration getConfig(String fileName) {
        return configSet.get(fileName).getConfig();
    }

    public static void loadSettings(){
        FileConfiguration StyleSystemConfig = getConfig("stylesystem");
        StyleSystemConfig.options().copyDefaults(true);
        StyleSystemConfig.addDefault("stylesystem.prefix", "&a&l[칭호]&7");
        StyleSystemConfig.addDefault("stylesystem.maxstylelength", 10);
        StyleSystemConfig.addDefault("stylesystem.maxnickength", 8);
        StyleSystemConfig.addDefault("stylesystem.host", "127.0.0.1");
        StyleSystemConfig.addDefault("stylesystem.port", "3306");
        StyleSystemConfig.addDefault("stylesystem.database", "minecraft");
        StyleSystemConfig.addDefault("stylesystem.username", "root");
        StyleSystemConfig.addDefault("stylesystem.password", "");
    }
}