package me.flamingkatana.mc.plugins.coloredanvils;

import me.flamingkatana.mc.plugins.coloredanvils.item.NameFilter;
import me.flamingkatana.mc.plugins.coloredanvils.listener.AnvilListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class ColoredAnvils extends JavaPlugin {

    private static ColoredAnvils plugin;
    private static NameFilter nameFilter;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        updateConfig();
        nameFilter = new NameFilter();
        registerEvents();
    }

    @Override
    public void onDisable() {
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
