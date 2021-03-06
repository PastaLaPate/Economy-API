package fr.pastalapate.economy_api.file;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.pastalapate.economy_api.EAPIMain;

public class DataManager {

	private EAPIMain main;
	private FileConfiguration dataConfig = null;
	private File configFile = null;
	
	public DataManager(EAPIMain main) {
		this.main = main;
		// saves/initializes the config
		saveDefaultConfig();
	}
	
	public void reloadConfig() {
		if(this.configFile == null)
			this.configFile = new File(this.main.getDataFolder(), "data.yml");
		
		this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
		
		InputStream defaultStream = this.main.getResource("data.yml");
		if(defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.dataConfig.setDefaults(defaultConfig);
		}
	}
	
	public FileConfiguration getConfig() {
		if(this.dataConfig == null)
		
			reloadConfig();
			
		return this.dataConfig;
	}
	
	public void saveConfig() {
		if (this.dataConfig == null || this.configFile == null)
			return;
		
		try {
			this.getConfig().save(this.configFile);
		} catch (IOException e) {
			main.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
		}
	}
	
	public void saveDefaultConfig() {
		if (this.configFile == null)
			this.configFile = new File(this.main.getDataFolder(), "data.yml");
		
		if (!this.configFile.exists()) {
			this.main.saveResource("data.yml", false);
		}
	}
	
	
}
