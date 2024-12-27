package org.hwabeag.stylesystem.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.hwabeag.stylesystem.config.ConfigManager;

public class JoinEvent implements Listener {
    FileConfiguration PlayerConfig = ConfigManager.getConfig("player");
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        if (PlayerConfig.getString(name + ".칭호목록") == null) {
            PlayerConfig.addDefault(name, "");
            PlayerConfig.set(name + ".칭호목록.\uE500", "\uE500");
            PlayerConfig.set(name + ".페이지", 0);
            PlayerConfig.set(name + ".적용칭호", "\uE500");
            PlayerConfig.set(name + ".적용한닉", "없음");
            ConfigManager.saveConfigs();
        }
    }
}
