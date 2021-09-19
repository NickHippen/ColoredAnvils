package me.flamingkatana.coloredanvils;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class AnvilTask extends BukkitRunnable {

	private static HashMap<AnvilInventory, AnvilTask> anvilTasks = new HashMap<>();

	private AnvilInventory inv;
	private Player player;

	public AnvilTask(AnvilInventory inv, Player player) {
		this.inv = inv;
		this.player = player;
		anvilTasks.put(inv, this);
		runTaskTimer(ColoredAnvils.getPlugin(), 1L, 3L);
	}

	@Override
	public void run() {
		if (inv.getViewers().size() == 0)
			cancel();
		ColorHandler.getTranslatedItem(player, inv, this);
	}

	public static AnvilTask getTask(AnvilInventory inv) {
		return anvilTasks.get(inv);
	}

}
