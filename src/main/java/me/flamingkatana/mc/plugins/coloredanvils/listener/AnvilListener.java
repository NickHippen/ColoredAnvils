package me.flamingkatana.mc.plugins.coloredanvils.listener;

import me.flamingkatana.mc.plugins.coloredanvils.item.ItemColorTranslator;
import me.flamingkatana.mc.plugins.coloredanvils.constant.AnvilConstants;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;

public class AnvilListener implements Listener {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        var anvilInventory = event.getInventory();
        ItemColorTranslator.applyColorTranslationToInventorySlot(anvilInventory, AnvilConstants.OUTPUT_SLOT);
    }

}
