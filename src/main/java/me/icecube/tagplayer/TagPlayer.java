package me.icecube.tagplayer;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class TagPlayer extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new ChatListener(),this);
        this.getServer().getLogger().info("Плагин TagPlayer успешно запущен!");
        this.getCommand("tag").setExecutor(new TagCommand());
    }

    public static FileConfiguration getPluginConfig(){
        return Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("tagPlayer")).getConfig();
    }

}
