package com.Lingyu.SleepAlways;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin implements Listener {
	int task1;
	// Fired when plugin is enabled
    @Override
    public void onEnable() {
    	PluginManager pm = this.getServer().getPluginManager();
    	pm.registerEvents(this, this);
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	getServer().getConsoleSender().sendMessage("SleepAlways is disable!");
    }
    
    @EventHandler
    public void onPlayerSleeping(PlayerBedEnterEvent event) {
    	task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
    		@Override
    		public void run() {
            	Player player = event.getPlayer();
    		if (player.isSleeping()) {
    		    CraftHumanEntity entity = (CraftPlayer) player;
    		    entity.getHandle().sleepTicks = 0;
    		} else {
    			Bukkit.getScheduler().cancelTask(task1);
    		}
            }
    	}, 100l, 100l);
    }
    @EventHandler(priority = EventPriority.HIGH)
	public void onPlayerSleepingChat(PlayerCommandPreprocessEvent event) {
    	Player player = event.getPlayer();
    	String message = "§7  無法使用指令,請先離開床上";
		if (player.isSleeping()) {
			event.setCancelled(true);
			player.spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(message));
		}
	}
}
