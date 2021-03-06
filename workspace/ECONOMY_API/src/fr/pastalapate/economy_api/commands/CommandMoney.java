package fr.pastalapate.economy_api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pastalapate.economy_api.EAPIMain;

public class CommandMoney implements CommandExecutor {

	private EAPIMain main;
	
	public CommandMoney(EAPIMain main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(main.getConfig().getBoolean("commands.money")) {
			money(sender, cmd, msg, args);
		} else {
			sender.sendMessage(main.getConfig().getString("commands-disabled"));
		}
		return true;
	}
	
	public void money(CommandSender sender, Command cmd, String msg, String[] args) {
		
		Player player = (Player) sender;
		try {
			sender.sendMessage(main.getConfig().getString("messages.money") + main.data.getConfig().getInt("players." + player.getUniqueId().toString() + ".money"));
		} catch(NullPointerException e) {
			System.out.println("Starting CommandMoney");
		}
	}

}
