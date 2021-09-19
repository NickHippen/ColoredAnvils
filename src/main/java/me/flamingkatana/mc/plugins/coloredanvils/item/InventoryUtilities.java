package me.flamingkatana.mc.plugins.coloredanvils.item;

import me.flamingkatana.mc.plugins.coloredanvils.ColoredAnvils;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryUtilities {

    public static void updateItemInInventorySlotDelayed(Inventory inventory, int index, ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                inventory.setItem(index, itemStack);
            }
        }.runTaskLater(ColoredAnvils.getPlugin(), 0L);
    }

}
