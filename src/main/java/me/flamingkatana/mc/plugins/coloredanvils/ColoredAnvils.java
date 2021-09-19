package me.flamingkatana.mc.plugins.coloredanvils;

import me.flamingkatana.mc.plugins.coloredanvils.listener.AnvilListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ColoredAnvils extends JavaPlugin {

    private static ColoredAnvils plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public static ColoredAnvils getPlugin() {
        return plugin;
    }

}
