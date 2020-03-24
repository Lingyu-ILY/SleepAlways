package com.Lingyu.SleepAlways;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

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
    	Player player = event.getPlayer();
    		if (player.isSleeping()) {
    		    CraftHumanEntity entity = (CraftPlayer) player;
    		    entity.getHandle().sleepTicks = 0;
    	}
    	
    }
}
