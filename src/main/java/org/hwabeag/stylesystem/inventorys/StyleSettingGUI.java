package org.hwabeag.stylesystem.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.hwabeag.stylesystem.config.ConfigManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class StyleSettingGUI implements Listener {
    private final Inventory inv;
    FileConfiguration PlayerConfig = ConfigManager.getConfig("player");

    private ItemStack getStyle(String StyleName) {
        ItemStack item = new ItemStack(Material.NAME_TAG, 1, (short) 3);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', StyleName));
        ArrayList<String> loreList = new ArrayList<>();
        loreList.add(ChatColor.translateAlternateColorCodes('&', "&f&l- 클릭시 칭호를 장착 혹 해제 합니다."));
        meta.setLore(loreList);
        item.setItemMeta(meta);
        return item;
    }

    private void initItemSetting(Player p) {

        String name = p.getName();
        int PlayerPage = PlayerConfig.getInt(name + ".페이지");

        int N = 0;
        int Page = 0;
        for (String key : Objects.requireNonNull(PlayerConfig.getConfigurationSection(name + ".칭호목록")).getKeys(false)) {
            if (Page == PlayerPage) {
                String style = Objects.requireNonNull(PlayerConfig.getString(name + ".칭호목록." + key));
                inv.setItem(N, getStyle(style));
            }
            N = N + 1;
            if (N >= 44) {
                Page = Page + 1;
                N = 0;
            }
        }

        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a이전 페이지"));
        itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&a- &f클릭 시 이전 페이지로 이동합니다.")));
        item.setItemMeta(itemMeta);
        inv.setItem(51,item);


        item = new ItemStack(Material.PAPER, 1);
        itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a다음 페이지"));
        itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&a- &f클릭 시 다음 페이지로 이동합니다.")));
        item.setItemMeta(itemMeta);
        inv.setItem(53,item);

    }

    public StyleSettingGUI(Player p) {
        this.inv = Bukkit.createInventory(null,54,"칭호 관리");
        initItemSetting(p);
    }

    public void open(Player player){
        player.openInventory(inv);
    }

}
