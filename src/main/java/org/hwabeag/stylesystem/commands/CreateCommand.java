package org.hwabeag.stylesystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.hwabeag.stylesystem.config.ConfigManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CreateCommand implements CommandExecutor {

    FileConfiguration StyleConfig = ConfigManager.getConfig("stylesystem");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(StyleConfig.getString("stylesystem.prefix")));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            Bukkit.getConsoleSender().sendMessage(Prefix + " 인게임에서 사용할 수 있습니다.");
            return true;
        }
        if (!p.isOp()){
            p.sendMessage(Prefix + " 당신은 권한이 없습니다.");
            return true;
        }
        if (args.length == 0) {
            p.sendMessage(Prefix + " /칭호생성 [칭호] - 칭호를 생성합니다.");
            return true;
        }
        if (StyleConfig.getString("stylesystem.list." + args[0]) == null) {
            int maxstylelength = StyleConfig.getInt("stylesystem.maxstylelength");
            int args_len = args[0].length();//길이 구하기
            if (args_len > maxstylelength) {
                p.sendMessage(Prefix + " " + args[0] + " 는 칭호 생성에 있어서 제한을 초과하였습니다.");
                p.sendMessage(Prefix + " 최대 글자수는 " + maxstylelength + " 글자 입니다.");
                return true;
            }
            String stylename = args[0];
            stylename = ChatColor.translateAlternateColorCodes('&', stylename);
            StyleConfig.set("stylesystem.list." + stylename, stylename);
            ConfigManager.saveConfigs();
            p.sendMessage(Prefix + " " + args[0] + " 의 칭호를 생성했습니다.");
        } else {
            p.sendMessage(Prefix + " " + args[0] + " 의 칭호는 이미 존재합니다.");
        }
        return true;
    }
}