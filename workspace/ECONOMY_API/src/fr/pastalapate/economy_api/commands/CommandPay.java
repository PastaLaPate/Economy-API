package fr.pastalapate.economy_api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pastalapate.economy_api.EAPIMain;

public class CommandPay implements CommandExecutor {

	private EAPIMain main;
	private Player to;
	
	public CommandPay(EAPIMain main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(main.getConfig().getBoolean("commands.pay")) {
			pay(sender, cmd, msg, args);	
		} else {
			sender.sendMessage(main.getConfig().getString("commands-disabled"));
		}
		return false;
	}
	
	public void pay(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		to = Bukkit.getPlayer(args[0]);
		if(!(args[0] == null) && !(args[1] == null)) {
			if(Bukkit.getServer().getOnlinePlayers().contains(to)) {
				Integer arg2 = Integer.parseInt(args[1]);
				System.out.println(arg2);
				if(main.data.getConfig().getInt("players." + player.getUniqueId().toString() + ".money") > arg2) {
					main.data.getConfig().set("players." + player.getUniqueId().toString() + ".money", (main.data.getConfig().getInt("players." + player.getUniqueId().toString() + ".money") - arg2));
					main.data.getConfig().set("players." + to.getUniqueId().toString() + ".money", main.data.getConfig().getInt("players." + to.getUniqueId().toString() + ".money") + arg2);
					main.data.saveConfig();
				} else {
					sender.sendMessage(main.getConfig().getString("messages.not-enough-money"));
				}
			}
		} else {
			sender.sendMessage(main.getConfig().getString("messages.player-not-found"));
		}
	}

}
