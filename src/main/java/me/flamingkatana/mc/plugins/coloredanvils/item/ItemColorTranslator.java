package me.flamingkatana.mc.plugins.coloredanvils.item;

import me.flamingkatana.mc.plugins.coloredanvils.ColoredAnvils;
import me.flamingkatana.mc.plugins.coloredanvils.constant.AnvilConstants;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemColorTranslator {

    public static void applyColorTranslationToInventorySlot(Inventory inventory, int index) {
        var itemStack = inventory.getItem(index);
        if (itemStack == null) {
            return;
        }
        var translatedItemStack = translateNameColor(itemStack);
        new BukkitRunnable() {
            @Override
            public void run() {
                inventory.setItem(index, translatedItemStack);
            }
        }.runTaskLater(ColoredAnvils.getPlugin(), 0L);
    }

    public static ItemStack translateNameColor(ItemStack itemStack) {
        var itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return itemStack;
        }
        var untranslatedName = itemMeta.getDisplayName();
        var translatedName = ChatColor.translateAlternateColorCodes('&', untranslatedName);
        itemMeta.setDisplayName(translatedName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
