package me.flamingkatana.coloredanvils;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public class RepairListener implements Listener {

	@EventHandler
	public void onAnvilGUIClick(InventoryClickEvent event) {
		if (event.getInventory().getType() == InventoryType.ANVIL)
			if (event.getWhoClicked() instanceof Player) {
				final Player player = (Player) event.getWhoClicked();
				AnvilInventory inv = (AnvilInventory) event.getInventory();
				AnvilTask task = AnvilTask.getTask(inv);
				if (task == null)
					task = new AnvilTask(inv, player);
				if (event.getRawSlot() == ColorHandler.OUTPUT) {
					ItemStack translatedItem = ColorHandler.getTranslatedItem(player, inv, task);
					List<String> illegalWords = FilterHandler.getIllegalWordsInItemName(translatedItem);
					if (illegalWords.size() != 0) {
						event.setCancelled(true);
						player.setExp(player.getExp());
						for (String word : illegalWords)
							player.sendMessage(ChatColor.RED + "Your item cannot contain the word " + ChatColor.BOLD + word + ChatColor.RED
									+ ".");
					}
					event.setCurrentItem(translatedItem);
				}
			}
	}
}
