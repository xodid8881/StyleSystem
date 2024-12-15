package org.hwabeag.stylesystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.hwabeag.stylesystem.config.ConfigManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NickChangeGiveCommand implements TabCompleter, @Nullable CommandExecutor {

    FileConfiguration StyleConfig = ConfigManager.getConfig("stylesystem");
    FileConfiguration PlayerConfig = ConfigManager.getConfig("player");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(StyleConfig.getString("stylesystem.prefix")));
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<String>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            list.add(p.getName());
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
            p.sendMessage(Prefix + " /한닉권 지급 [닉네임] - 칭호를 지급합니다.");
            return true;
        }

        if (args[0].equalsIgnoreCase("지급")) {
            if (args.length == 1) {
                p.sendMessage(Prefix + " /한닉권 지급 [닉네임] - 칭호를 지급합니다.");
                p.sendMessage(Prefix + " 한닉을 정확하게 적어주세요.");
                return true;
            }

            ItemStack item = new ItemStack(Material.BOOK, 1, (short) 3);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&l[한닉권]"));
            ArrayList<String> loreList = new ArrayList<>();
            loreList.add(ChatColor.translateAlternateColorCodes('&', "&f&l- 손에 들고 /한닉관리 명령어를 이용하세요."));
            meta.setLore(loreList);
            item.setItemMeta(meta);
            for (Player ignored : Bukkit.getOnlinePlayers()) {
                if (ignored.getName().equals(args[1])) {
                    ignored.getInventory().addItem(item);
                    p.sendMessage(Prefix + " " + ignored.getName() + " 님에게 한닉권을 지급했습니다.");
                    ignored.sendMessage(Prefix + " 한닉권을 지급 받았습니다.");
                    return true;
                }
            }
            p.sendMessage(Prefix + " 해당 유저는 존재하지 않습니다.");
            return true;
        }
        p.sendMessage(Prefix + " /한닉권 지급 [닉네임] - 칭호를 지급합니다.");
        return true;
    }
}
