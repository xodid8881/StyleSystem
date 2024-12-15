package org.hwabeag.stylesystem.expansions;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.hwabeag.stylesystem.StyleSystem;
import org.hwabeag.stylesystem.config.ConfigManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StyleExpansion extends PlaceholderExpansion {


    FileConfiguration PlayerConfig = ConfigManager.getConfig("player");
    private final StyleSystem plugin; //

    public StyleExpansion(StyleSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "style";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("getstyle")) { // %style_getstyle%
            String name = Objects.requireNonNull(player).getName();
            if (PlayerConfig.getString(name + ".적용칭호") != null) {
                if (Objects.equals(PlayerConfig.getString(name + ".적용칭호"), "없음")) {
                    return "&l&a[&f서버원&l&a]";
                } else {
                    return PlayerConfig.getString(name + ".적용칭호");
                }
            }
        }
        if (params.equalsIgnoreCase("getnick")) { // %style_getnick%
            String name = Objects.requireNonNull(player).getName();
            if (PlayerConfig.getString(name + ".적용한닉") != null) {
                if (Objects.equals(PlayerConfig.getString(name + ".적용한닉"), "없음")) {
                    return name;
                } else {
                    return PlayerConfig.getString(name + ".적용한닉");
                }
            }
        }
        return null; //
    }
}