package fr.pastalapate.economy_api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.pastalapate.economy_api.commands.CommandMenuEconomy;
import fr.pastalapate.economy_api.commands.CommandMoney;
import fr.pastalapate.economy_api.commands.CommandPay;
import fr.pastalapate.economy_api.commands.CommandReload;
import fr.pastalapate.economy_api.file.DataManager;
import fr.pastalapate.economy_api.listeners.GPlayerListeners;

public class EAPIMain extends JavaPlugin {

	public DataManager data;
	private CommandMenuEconomy menu;
	
	@Override
	public void onEnable() {
		this.data = new DataManager(this);
		saveDefaultConfig();
		System.out.println("Economy API enabled");
		getCommand("money").setExecutor(new CommandMoney(this));
		getCommand("pay").setExecutor(new CommandPay(this));
		getCommand("eapi_rl").setExecutor(new CommandReload(this));
		getCommand("menu_economy").setExecutor(menu = new CommandMenuEconomy(this));
		menu.createInv();
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new GPlayerListeners(this), this);
		pm.registerEvents(new CommandMenuEconomy(this), this);
	}
	
	@Override
	public void onDisable() {
		data.saveConfig();
		System.out.println("Economy API disabled");
	}
	
	public int getMoney(Player player) {
		try {
		return data.getConfig().getInt("players." + player.getUniqueId().toString() + ".money");
		} catch (NullPointerException e) {
			return 0;
		}
	}
	
}