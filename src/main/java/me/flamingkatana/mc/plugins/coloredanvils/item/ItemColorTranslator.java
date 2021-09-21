package me.flamingkatana.mc.plugins.coloredanvils.item;

import me.flamingkatana.mc.plugins.coloredanvils.ColoredAnvils;
import me.flamingkatana.mc.plugins.coloredanvils.constant.AnvilConstants;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemColorTranslator {

    public static void updateColorEncodingForAnvilOutput(AnvilInventory anvilInventory, HumanEntity humanEntity) {
        new BukkitRunnable() {
            @Override
            public void run() {
                var inputItem = anvilInventory.getItem(AnvilConstants.FIRST_INPUT_SLOT);
                if (inputItem == null) {
                    return;
                }
                var outputItem = anvilInventory.getItem(AnvilConstants.OUTPUT_SLOT);
                if (outputItem == null) {
                    return;
                }
                ItemColorTranslator.encodeOutputItemNameColorBasedOnInputItem(outputItem, inputItem, humanEntity);
            }
        }.runTaskLater(ColoredAnvils.getPlugin(), 0L);
    }

    public static ItemStack encodeOutputItemNameColorBasedOnInputItem(ItemStack outputItem, ItemStack inputItem, HumanEntity humanEntity) {
        var outputItemMeta = outputItem.getItemMeta();
        if (outputItemMeta == null || !outputItemMeta.hasDisplayName()) {
            return outputItem;
        }
        var outputName = outputItemMeta.getDisplayName();
        var inputItemMeta = inputItem.getItemMeta();
        if (inputItemMeta == null || !inputItemMeta.hasDisplayName()) {
            return encodeNameColorWithPermissions(outputItem, humanEntity);
        }
        var inputName = inputItemMeta.getDisplayName();
        if (doesOutputNameMatchInputName(outputName, inputName)) {
            outputItemMeta.setDisplayName(inputName);
            outputItem.setItemMeta(outputItemMeta);
            if (!ColoredAnvils.getPermissionValidator().arePermissionsEnabledForNoChange()) {
                return outputItem;
            }
        }
        return encodeNameColorWithPermissions(outputItem, humanEntity);
    }

    public static ItemStack encodeNameColorWithPermissions(ItemStack itemStack, HumanEntity humanEntity) {
        var itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return itemStack;
        }
        var decodedName = itemMeta.getDisplayName();
        var encodedName = ChatColor.translateAlternateColorCodes(AnvilConstants.DECODED_COLOR_CHAR, decodedName);
        var permissionEnforcedEncodedName = ColoredAnvils.getPermissionValidator().enforcePermissionsOnName(humanEntity, encodedName);
        itemMeta.setDisplayName(permissionEnforcedEncodedName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static boolean doesOutputNameMatchInputName(String outputName, String inputName) {
        return stripChars(outputName, AnvilConstants.DECODED_COLOR_CHAR, ChatColor.COLOR_CHAR)
                .equals(stripChars(inputName, ChatColor.COLOR_CHAR));
    }

    private static String stripChars(String str, char... chars) {
        String strippedStr = str;
        for (char c : chars) {
            strippedStr = str.replaceAll(String.valueOf(c), "");
        }
        return strippedStr;
    }

}
