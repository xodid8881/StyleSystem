package org.hwabeag.stylesystem.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.hwabeag.stylesystem.config.ConfigManager;

import java.util.List;
import java.util.Objects;

public class BookClickEvent implements Listener {
    FileConfiguration StyleConfig = ConfigManager.getConfig("stylesystem");
    FileConfiguration PlayerConfig = ConfigManager.getConfig("player");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(StyleConfig.getString("stylesystem.prefix")));

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        String name = player.getName();
        if (player.getItemInHand().getItemMeta() == null) return;
        String itemname = player.getItemInHand().getItemMeta().getDisplayName();
        List<String> itemLore = player.getInventory().getItemInMainHand().getItemMeta().getLore();
        if (player.getItemInHand().getType() == Material.BOOK) {
            if (itemname.equals(ChatColor.translateAlternateColorCodes('&', "&a&l[칭호권]"))) {
                String tier = Objects.requireNonNull(itemLore).get(0);
                tier = ChatColor.translateAlternateColorCodes('&', tier);
                tier = tier.replace("&f&l- 사용시 &a&l", "");
                tier = tier.replace(" &f칭호 추가", "");
                player.sendMessage(Prefix + " 칭호권을 사용하여 " + tier + " 칭호를 지급 받았습니다.");
                ItemStack item = player.getInventory().getItemInMainHand();
                item.setAmount(item.getAmount() - 1);
                PlayerConfig.set(name + ".칭호목록." + tier, tier);
                ConfigManager.saveConfigs();
            }
        }
    }
}
