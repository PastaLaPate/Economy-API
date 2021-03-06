package fr.pastalapate.economy_api.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.pastalapate.economy_api.EAPIMain;

public class CommandMenuEconomy implements CommandExecutor, Listener{

	private EAPIMain main;
	public Inventory inv;
	private Player player;

	public CommandMenuEconomy(EAPIMain main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(main.getConfig().getBoolean("commands.menu_economy")) {
			if(label.equalsIgnoreCase("menu_economy")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage("You cannot do this !");
					return true;
				}
				player = (Player) sender;
				//Open GUI

				player.openInventory(inv);
				reloadMoney();
				player.updateInventory();
				
				
			}
			
		}
		return false;
	}
	
	public void createInv() {
		inv = Bukkit.createInventory(null, 9,ChatColor.BLACK + "" + ChatColor.BOLD + "Economy Menu");
		
		ItemStack item = new ItemStack(Material.GOLD_INGOT);
		ItemMeta itemMeta = item.getItemMeta();
		
		itemMeta.setDisplayName(main.getConfig().getString("messages.money") + main.getMoney(player));
		item.setItemMeta(itemMeta);
		inv.setItem(0, item);
		
		item.setType(Material.BARRIER);
		itemMeta.setDisplayName("Close menu");
		item.setItemMeta(itemMeta);
		
		inv.setItem(8, item);
		
	}
	
	public void reloadMoney() {
		ItemStack money = new ItemStack(Material.GOLD_INGOT);
		ItemMeta moneyMeta = money.getItemMeta();
		
		moneyMeta.setDisplayName(main.getConfig().getString("messages.money") + main.getMoney(player));
		money.setItemMeta(moneyMeta);
		
		ItemStack closeMenu = new ItemStack(Material.BARRIER);
		ItemMeta closeMenuMeta = closeMenu.getItemMeta();
		
		closeMenuMeta.setDisplayName("Close menu");
		closeMenu.setItemMeta(closeMenuMeta);
		
		inv.setItem(0, money);
		inv.setItem(8, closeMenu);
	}
	
	
	@EventHandler()
	public void onClick(InventoryClickEvent e) {
		if (e.getView().getTitle().equals(ChatColor.BLACK + "" + ChatColor.BOLD + "Economy Menu")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getItemMeta() == null) {
				return;
			}
			e.getWhoClicked().sendMessage("3");
			if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
				return;
			}
			
			
			Player player = (Player) e.getWhoClicked(); 
			if (e.getSlot() == 8) {
				player.closeInventory();
			}else if(e.getSlot() == 0) {
				player.sendMessage(main.getConfig().getString("messages.money") + main.data.getConfig().getInt("players." + player.getUniqueId().toString() + ".money"));
				player.closeInventory();
			}
			
		}
	}
	
}
