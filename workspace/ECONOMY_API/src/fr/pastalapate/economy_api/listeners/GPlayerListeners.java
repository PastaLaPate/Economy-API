package fr.pastalapate.economy_api.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.pastalapate.economy_api.EAPIMain;

public class GPlayerListeners implements Listener {

	private EAPIMain main;
	
	public GPlayerListeners(EAPIMain main) {
		this.main = main;
	}
		
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		if(!main.data.getConfig().contains("players." + player.getUniqueId().toString() + ".money")) {
			main.data.getConfig().set("players." + player.getUniqueId().toString() + ".money", 0);
			main.data.saveConfig();
		}
		
		main.data.saveConfig();
	}
}
