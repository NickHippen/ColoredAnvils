package me.flamingkatana.mc.plugins.coloredanvils.item;

import me.flamingkatana.mc.plugins.coloredanvils.ColoredAnvils;
import me.flamingkatana.mc.plugins.coloredanvils.constant.AnvilConstants;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;

public class PermissionValidator {

    private final boolean arePermissionsEnabled;
    private final boolean arePermissionsEnabledForNoChange;

    public PermissionValidator() {
        arePermissionsEnabled = ColoredAnvils.getPlugin().getConfig().getBoolean("Use Permissions");
        arePermissionsEnabledForNoChange = ColoredAnvils.getPlugin().getConfig().getBoolean("Use_Permissions_If_Not_Changing_Name");
    }

    public boolean hasColorPermission(HumanEntity humanEntity, char colorCode) {
        return !arePermissionsEnabled()
                || humanEntity.hasPermission("coloredanvils.*")
                || humanEntity.hasPermission("coloredanvils.color.*")
                || humanEntity.hasPermission("coloredanvils.color." + colorCode)
                || humanEntity.hasPermission("coloredanvils.color.&" + colorCode);
    }

    public String enforcePermissionsOnName(HumanEntity humanEntity, String name) {
        String enforcedName = name;
        for (int i = 0; i < name.length() - 1; i++) {
            char c = name.charAt(i);
            if (c != ChatColor.COLOR_CHAR) {
                continue;
            }
            char colorCode = name.charAt(i + 1);
            if (hasColorPermission(humanEntity, colorCode)) {
                continue;
            }
            enforcedName = enforcedName.replaceAll(ChatColor.COLOR_CHAR + "" + colorCode, AnvilConstants.DECODED_COLOR_CHAR + "" + colorCode);
        }
        return enforcedName;
    }

    public boolean arePermissionsEnabled() {
        return arePermissionsEnabled;
    }

    public boolean arePermissionsEnabledForNoChange() {
        return arePermissionsEnabledForNoChange;
    }
}
