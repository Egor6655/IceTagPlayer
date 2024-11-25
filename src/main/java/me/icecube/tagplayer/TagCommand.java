package me.icecube.tagplayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static me.icecube.tagplayer.TagPlayer.getPluginConfig;

public class TagCommand implements CommandExecutor {
    public static Set<Player> disabledPlayers = new HashSet<>();

    public static void setPlayerToList(Player p){
        disabledPlayers.add(p);
    }
    public static void deletePlayerfromList(Player p){
        disabledPlayers.remove(p);
    }
    public static Boolean PlayerIsInList(Player p){

        if(disabledPlayers.isEmpty()){
            return false;
        } else if (disabledPlayers.contains(p)) {
            return true;

        }else{
            return false;
        }

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player CmdSender){
            if(PlayerIsInList(CmdSender)){
                deletePlayerfromList(CmdSender);
                ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getPluginConfig().getString("TagDisabled")));
            } else{
                setPlayerToList(CmdSender);
                ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getPluginConfig().getString("TagEnabled")));
            }
        }
        return true;
    }
}
