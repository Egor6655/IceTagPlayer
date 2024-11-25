package me.icecube.tagplayer;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;


import static me.icecube.tagplayer.TagCommand.PlayerIsInList;
import static me.icecube.tagplayer.TagPlayer.getPluginConfig;


public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerTagInChat(PlayerChatEvent e){
        String message = e.getMessage();
        List<String> words = Arrays.stream(message.split("\\s+")).toList();
        if(e.getPlayer().hasPermission("tag.use")) {

            for (Player p : Bukkit.getOnlinePlayers()) {
                String name = "@" + p.getName();
                for (String word : words) {
                    if (word.equals(name)) {
                        if(!PlayerIsInList(p)) {
                            if(getPluginConfig().getString("type").equals("title")) {
                                p.sendTitle("", ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getPluginConfig().getString("title")).replace("%player%",e.getPlayer().getName())), 20, getPluginConfig().getInt("time") * 20, 20);
                                p.getWorld().playSound(p.getLocation(), Sound.valueOf(getPluginConfig().getString("Sound")), 2, 2);
                            }else if(getPluginConfig().getString("type").equals("message")){
                                List<String> messages = getPluginConfig().getStringList("message");
                                for(String stroke : messages){
                                    String newStroke = stroke.replace( "%player%",e.getPlayer().getName());
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',newStroke));
                                    p.getWorld().playSound(p.getLocation(), Sound.valueOf(getPluginConfig().getString("Sound")), 2, 2);
                                }
                            }
                        }else{
                            e.getPlayer().sendMessage(ChatColor.RED + "Этот игрок запретил его тегать!");
                        }
                    }
                }
            }
        }

    }

}
