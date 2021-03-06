package fr.pastalapate.economy_api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.pastalapate.economy_api.EAPIMain;

public class CommandReload implements CommandExecutor {

	private EAPIMain main;
	
	public CommandReload(EAPIMain main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		main.data.reloadConfig();
		return false;
	}

}
