package org.hwabeag.stylesystem.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.hwabeag.stylesystem.StyleSystem;
import org.hwabeag.stylesystem.config.ConfigManager;
import org.hwabeag.stylesystem.inventorys.StyleDeleteGUI;
import org.hwabeag.stylesystem.inventorys.StyleGiveGUI;
import org.hwabeag.stylesystem.inventorys.StyleListGUI;
import org.hwabeag.stylesystem.inventorys.StyleSettingGUI;

import java.util.ArrayList;
import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class InvClickEvent implements Listener {
    FileConfiguration StyleConfig = ConfigManager.getConfig("stylesystem");
    FileConfiguration PlayerConfig = ConfigManager.getConfig("player");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(StyleConfig.getString("stylesystem.prefix")));


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null)
            return;
        if (e.getCurrentItem() != null) {
            if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("칭호 리스트")) {
                String clickitem = e.getCurrentItem().getItemMeta().getDisplayName();
                Player player = (Player) e.getWhoClicked();
                String name = player.getName();
                e.setCancelled(true);
                if (clickitem.equals(ChatColor.translateAlternateColorCodes('&', "&a이전 페이지"))) {
                    e.getInventory().clear();
                    player.closeInventory();
                    int page = PlayerConfig.getInt(name + ".페이지");
                    int plus = page - 1;
                    PlayerConfig.set(name + ".페이지", plus);
                    ConfigManager.saveConfigs();
                    getServer().getScheduler().scheduleSyncDelayedTask(StyleSystem.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            StyleListGUI inv = new StyleListGUI(player);
                            inv.open(player);
                        }
                    }, 20);
                }
                if (clickitem.equals(ChatColor.translateAlternateColorCodes('&', "&a다음 페이지"))) {
                    e.getInventory().clear();
                    player.closeInventory();
                    int page = PlayerConfig.getInt(name + ".페이지");
                    int plus = page + 1;
                    PlayerConfig.set(name + ".페이지", plus);
                    ConfigManager.saveConfigs();
                    getServer().getScheduler().scheduleSyncDelayedTask(StyleSystem.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            StyleListGUI inv = new StyleListGUI(player);
                            inv.open(player);
                        }
                    }, 20);
                }
            }
            if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("칭호 제거")) {
                String clickitem = e.getCurrentItem().getItemMeta().getDisplayName();
                Player player = (Player) e.getWhoClicked();
                String name = player.getName();
                e.setCancelled(true);
                if (clickitem.equals(ChatColor.translateAlternateColorCodes('&', "&a이전 페이지"))) {
                    e.getInventory().clear();
                    player.closeInventory();
                    int page = PlayerConfig.getInt(name + ".페이지");
                    int plus = page - 1;
                    PlayerConfig.set(name + ".페이지", plus);
                    ConfigManager.saveConfigs();
                    getServer().getScheduler().scheduleSyncDelayedTask(StyleSystem.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            StyleDeleteGUI inv = new StyleDeleteGUI(player);
                            inv.open(player);
                        }
                    }, 20);
                }
                if (clickitem.equals(ChatColor.translateAlternateColorCodes('&', "&a다음 페이지"))) {
                    e.getInventory().clear();
                    player.closeInventory();
                    int page = PlayerConfig.getInt(name + ".페이지");
                    int plus = page + 1;
                    PlayerConfig.set(name + ".페이지", plus);
                    ConfigManager.saveConfigs();
                    getServer().getScheduler().scheduleSyncDelayedTask(StyleSystem.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            StyleDeleteGUI inv = new StyleDeleteGUI(player);
                            inv.open(player);
                        }
                    }, 20);
                }
                StyleConfig.set("stylesystem.list." + clickitem, null);
                ConfigManager.saveConfigs();
                player.sendMessage(Prefix + " " + clickitem + " 칭호를 제거했습니다.");
                player.closeInventory();
                return;
            }
            if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("칭호 지급")) {
                String clickitem = e.getCurrentItem().getItemMeta().getDisplayName();
                Player player = (Player) e.getWhoClicked();
                String name = player.getName();
                e.setCancelled(true);
                if (clickitem.equals(ChatColor.translateAlternateColorCodes('&', "&a이전 페이지"))) {
                    e.getInventory().clear();
                    player.closeInventory();
                    int page = PlayerConfig.getInt(name + ".페이지");
                    int plus = page - 1;
                    PlayerConfig.set(name + ".페이지", plus);
                    ConfigManager.saveConfigs();
                    getServer().getScheduler().scheduleSyncDelayedTask(StyleSystem.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            StyleGiveGUI inv = new StyleGiveGUI(player);
                            inv.open(player);
                        }
                    }, 20);
                }
                if (clickitem.equals(ChatColor.translateAlternateColorCodes('&', "&a다음 페이지"))) {
                    e.getInventory().clear();
                    player.closeInventory();
                    int page = PlayerConfig.getInt(name + ".페이지");
                    int plus = page + 1;
                    PlayerConfig.set(name + ".페이지", plus);
                    ConfigManager.saveConfigs();
                    getServer().getScheduler().scheduleSyncDelayedTask(StyleSystem.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            StyleGiveGUI inv = new StyleGiveGUI(player);
                            inv.open(player);
                        }
                    }, 20);
                }
                ItemStack item = new ItemStack(Material.BOOK, 1, (short) 3);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&l[칭호권]"));
                ArrayList<String> loreList = new ArrayList<>();
                loreList.add(ChatColor.translateAlternateColorCodes('&', clickitem));
                loreList.add(ChatColor.translateAlternateColorCodes('&', "&f&l- 사용시 칭호를 획득합니다."));
                loreList.add(ChatColor.translateAlternateColorCodes('&', "&f&l- &a&l땅에 클릭시 사용됩니다."));
                meta.setLore(loreList);
                item.setItemMeta(meta);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Objects.equals(PlayerConfig.getString(name + ".지급유저"), p.getName())) {
                        p.getInventory().addItem(item);
                        p.sendMessage(Prefix + " 운영진으로 부터 칭호권을 지급받았습니다.");
                        player.sendMessage(Prefix + " " + p.getName() + " 님에게 칭호권을 지급했습니다.");
                        player.closeInventory();
                        return;
                    }
                }
            }
            if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("칭호 관리")) {
                String clickitem = e.getCurrentItem().getItemMeta().getDisplayName();
                Player player = (Player) e.getWhoClicked();
                String name = player.getName();
                e.setCancelled(true);
                if (clickitem.equals(ChatColor.translateAlternateColorCodes('&', "&a이전 페이지"))) {
                    e.getInventory().clear();
                    player.closeInventory();
                    int page = PlayerConfig.getInt(name + ".페이지");
                    int plus = page - 1;
                    PlayerConfig.set(name + ".페이지", plus);
                    ConfigManager.saveConfigs();
                    getServer().getScheduler().scheduleSyncDelayedTask(StyleSystem.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            StyleSettingGUI inv = new StyleSettingGUI(player);
                            inv.open(player);
                        }
                    }, 20);
                }
                if (clickitem.equals(ChatColor.translateAlternateColorCodes('&', "&a다음 페이지"))) {
                    e.getInventory().clear();
                    player.closeInventory();
                    int page = PlayerConfig.getInt(name + ".페이지");
                    int plus = page + 1;
                    PlayerConfig.set(name + ".페이지", plus);
                    ConfigManager.saveConfigs();
                    getServer().getScheduler().scheduleSyncDelayedTask(StyleSystem.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            StyleSettingGUI inv = new StyleSettingGUI(player);
                            inv.open(player);
                        }
                    }, 20);
                }
                if (Objects.equals(PlayerConfig.getString(name + ".적용칭호"), "없음")) {
                    PlayerConfig.set(name + ".적용칭호", clickitem);
                    ConfigManager.saveConfigs();
                    player.sendMessage(Prefix + " " + clickitem + " 칭호를 장착했습니다.");
                } else if (Objects.equals(PlayerConfig.getString(name + ".적용칭호"), clickitem)) {
                    PlayerConfig.set(name + ".적용칭호", "없음");
                    ConfigManager.saveConfigs();
                    player.sendMessage(Prefix + " " + clickitem + " 칭호를 해제했습니다.");
                } else if (!Objects.equals(PlayerConfig.getString(name + ".적용칭호"), clickitem)) {
                    PlayerConfig.set(name + ".적용칭호", clickitem);
                    ConfigManager.saveConfigs();
                    player.sendMessage(Prefix + " " + clickitem + " 칭호를 장착했습니다.");
                }
                player.closeInventory();
            }
        }
    }
}
