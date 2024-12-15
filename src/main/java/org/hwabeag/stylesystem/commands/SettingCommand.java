package org.hwabeag.stylesystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.hwabeag.stylesystem.config.ConfigManager;
import org.hwabeag.stylesystem.inventorys.StyleSettingGUI;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SettingCommand implements CommandExecutor {

    FileConfiguration StyleConfig = ConfigManager.getConfig("stylesystem");
    FileConfiguration PlayerConfig = ConfigManager.getConfig("player");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(StyleConfig.getString("stylesystem.prefix")));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            Bukkit.getConsoleSender().sendMessage(Prefix + " 인게임에서 사용할 수 있습니다.");
            return true;
        }

        PlayerConfig.set(p.getName() + ".페이지", 0);
        ConfigManager.saveConfigs();

        StyleSettingGUI inv = new StyleSettingGUI(p);
        inv.open(p);
        return true;
    }
}