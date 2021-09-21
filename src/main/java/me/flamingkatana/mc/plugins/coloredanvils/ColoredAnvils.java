package me.flamingkatana.mc.plugins.coloredanvils;

import me.flamingkatana.mc.plugins.coloredanvils.item.NameFilter;
import me.flamingkatana.mc.plugins.coloredanvils.item.PermissionValidator;
import me.flamingkatana.mc.plugins.coloredanvils.listener.AnvilListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class ColoredAnvils extends JavaPlugin {

    private static ColoredAnvils plugin;
    private static NameFilter nameFilter;
    private static PermissionValidator permissionValidator;

    @Override
    public void onEnable() {
        enableMessage();
        plugin = this;
        saveDefaultConfig();
        updateConfig();
        nameFilter = new NameFilter();
        permissionValidator = new PermissionValidator();
        registerEvents();
    }

    @Override
    public void onDisable() {
        disableMessage();
    }

    private void enableMessage() {
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info(pdfFile.getName() + " v" + pdfFile.getVersion() + " has been enabled!");
    }

    private void disableMessage() {
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info(pdfFile.getName() + " v" + pdfFile.getVersion() + " has been disabled!");
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
    }

    public static ColoredAnvils getPlugin() {
        return plugin;
    }

    public static NameFilter getNameFilter() {
        return nameFilter;
    }

    public static PermissionValidator getPermissionValidator() {
        return permissionValidator;
    }

    public static void updateConfig() {
        Map<String, Object> map = getPlugin().getConfig().getValues(false);
        if (!map.containsKey("Use_Permissions_If_Not_Changing_Name"))
            getPlugin().getConfig().set("Use_Permissions_If_Not_Changing_Name", false);
        if (!map.containsKey("Filter_Enabled"))
            getPlugin().getConfig().set("Filter_Enabled", false);
        if (!map.containsKey("Filter"))
            getPlugin().getConfig().set("Filter", new String[] { "Filter", "Example" });
        getPlugin().saveConfig();
    }

}
