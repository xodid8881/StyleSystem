package org.hwabeag.stylesystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.hwabeag.stylesystem.config.ConfigManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NickChangeCommand implements CommandExecutor {

    FileConfiguration StyleConfig = ConfigManager.getConfig("stylesystem");
    FileConfiguration PlayerConfig = ConfigManager.getConfig("player");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(StyleConfig.getString("stylesystem.prefix")));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            Bukkit.getConsoleSender().sendMessage(Prefix + " 인게임에서 사용할 수 있습니다.");
            return true;
        }
        if (args.length == 0) {
            p.sendMessage(Prefix + " /한닉관리 설정 [한닉] - 한닉을 설정합니다.");
            return true;
        }
        if (args[0].equalsIgnoreCase("설정")) {
            if (args.length == 1) {
                p.sendMessage(Prefix + " /한닉관리 설정 [한닉] - 한닉을 설정합니다.");
                p.sendMessage(Prefix + " 한닉을 정확하게 적어주세요.");
                return true;
            }
            int maxnickength = StyleConfig.getInt("stylesystem.maxnickength");
            int args_len = args[1].length();//길이 구하기
            if (args_len > maxnickength) {
                p.sendMessage(Prefix + " " + args[0] + " 는 한닉설정에 있어서 최대 글자수를 초과했습니다.");
                p.sendMessage(Prefix + " 최대 글자수는 " + maxnickength +" 글자 입니다.");
                return true;
            }
            if (p.getItemInHand().getItemMeta() == null) {
                p.sendMessage(Prefix + " 손에 한닉권을 들고 진행해주세요.");
                return true;
            }
            String itemname = p.getItemInHand().getItemMeta().getDisplayName();
            if (p.getItemInHand().getType() == Material.BOOK) {
                if (itemname.equals(ChatColor.translateAlternateColorCodes('&', "&a&l[한닉권]"))) {
                    PlayerConfig.set(p.getName() + ".적용한닉", args[1]);
                    ConfigManager.saveConfigs();
                    p.sendMessage(Prefix + " " + args[1] + " 로 한닉을 설정했습니다.");

                    ItemStack item = p.getInventory().getItemInMainHand();
                    item.setAmount(item.getAmount() - 1);
                    return true;
                }
            }
            p.sendMessage(Prefix + " 손에 한닉권을 들고 진행해주세요.");
            return true;
        }
        p.sendMessage(Prefix + " /한닉관리 설정 [한닉] - 한닉을 설정합니다.");
        return true;
    }
}