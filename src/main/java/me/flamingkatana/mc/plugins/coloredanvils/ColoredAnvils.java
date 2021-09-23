package me.flamingkatana.mc.plugins.coloredanvils;

import me.flamingkatana.mc.plugins.coloredanvils.item.ItemColorTranslator;
import me.flamingkatana.mc.plugins.coloredanvils.item.NameFilter;
import me.flamingkatana.mc.plugins.coloredanvils.item.PermissionValidator;
import me.flamingkatana.mc.plugins.coloredanvils.listener.AnvilListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ColoredAnvils extends JavaPlugin {

    private static ColoredAnvils plugin;
    private static ItemColorTranslator itemColorTranslator;
    private static NameFilter nameFilter;
    private static PermissionValidator permissionValidator;

    @Override
    public void onEnable() {
        plugin = this;
        enableMessage();
        saveDefaultConfig();
        updateConfig();
        itemColorTranslator = new ItemColorTranslator();
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
        getLogger().info(getPlugin().getServer().getVersion() + "   " + getPlugin().getServer().getBukkitVersion());
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

    public static ItemColorTranslator itemColorTranslator() {
        return itemColorTranslator;
    }

    public static NameFilter nameFilter() {
        return nameFilter;
    }

    public static PermissionValidator permissionValidator() {
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
        if (!map.containsKey("Filter_Message"))
            getPlugin().getConfig().set("Filter_Message", "Your item cannot contain the word ");
        getPlugin().saveConfig();
    }

}
