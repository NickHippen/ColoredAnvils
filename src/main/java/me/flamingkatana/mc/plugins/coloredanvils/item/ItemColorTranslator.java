package me.flamingkatana.mc.plugins.coloredanvils.item;

import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemColorTranslator {

    public static void applyColorTranslationToInventorySlot(Inventory inventory, int index) {
        var itemStack = inventory.getItem(index);
        if (itemStack == null) {
            return;
        }
        var translatedItemStack = translateNameColor(itemStack);
        InventoryUtilities.updateItemInInventorySlotDelayed(inventory, index, translatedItemStack);
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
