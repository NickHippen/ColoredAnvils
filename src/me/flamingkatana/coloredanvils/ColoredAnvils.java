package me.flamingkatana.coloredanvils;

import java.util.Map;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class ColoredAnvils extends JavaPlugin {

	private static ColoredAnvils plugin;
	private static boolean permissions;
	private static boolean permissionsForNonNameChanges;
	private static boolean filters;

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().info(pdfFile.getName() + " v" + pdfFile.getVersion() + " has been enabled!");
		plugin = this;
		this.getServer().getPluginManager().registerEvents(new RepairListener(), this);
		this.saveDefaultConfig();
		updateConfig();
		permissions = this.getConfig().getBoolean("Use Permissions");
		permissionsForNonNameChanges = this.getConfig().getBoolean("Use_Permissions_If_Not_Changing_Name");
		filters = this.getConfig().getBoolean("Filter_Enabled");
		getLogger().info("Permissions for " + pdfFile.getName() + " are " + (usingPermissions() ? "enabled" : "disabled") + ".");
	}

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().info(pdfFile.getName() + " v" + pdfFile.getVersion() + " has been disabled!");
	}

	public static ColoredAnvils getPlugin() {
		return plugin;
	}

	public static boolean usingPermissions() {
		return permissions;
	}

	public static boolean usingPermissionsForNonNameChanges() {
		return permissionsForNonNameChanges;
	}

	public static boolean usingFilters() {
		return filters;
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
