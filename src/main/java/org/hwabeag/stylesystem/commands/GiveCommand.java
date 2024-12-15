package org.hwabeag.stylesystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.hwabeag.stylesystem.config.ConfigManager;
import org.hwabeag.stylesystem.inventorys.StyleGiveGUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GiveCommand implements TabCompleter, @Nullable CommandExecutor {

    FileConfiguration StyleConfig = ConfigManager.getConfig("stylesystem");
    FileConfiguration PlayerConfig = ConfigManager.getConfig("player");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(StyleConfig.getString("stylesystem.prefix")));
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = null;
        if (args.length == 1) {
            list = new ArrayList<String>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                list.add(p.getName());
            }
        }
        return list;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            Bukkit.getConsoleSender().sendMessage(Prefix + " 인게임에서 사용할 수 있습니다.");
            return true;
        }
        if (!p.isOp()){
            p.sendMessage(Prefix + " 당신은 권한이 없습니다.");
            return true;
        }
        if (args.length == 0) {
            p.sendMessage(Prefix + " /칭호지급 [닉네임] - 칭호를 지급합니다.");
            return true;
        }

        PlayerConfig.set(p.getName() + ".지급유저", args[0]);
        PlayerConfig.set(p.getName() + ".페이지", 0);
        ConfigManager.saveConfigs();

        StyleGiveGUI inv = new StyleGiveGUI(p);
        inv.open(p);
        return true;
    }
}
