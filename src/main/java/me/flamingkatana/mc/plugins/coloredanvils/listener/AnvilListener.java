package me.flamingkatana.mc.plugins.coloredanvils.listener;

import me.flamingkatana.mc.plugins.coloredanvils.ColoredAnvils;
import me.flamingkatana.mc.plugins.coloredanvils.constant.AnvilConstants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public class AnvilListener implements Listener {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        var anvilInventory = event.getInventory();
        var humanEntity = event.getView().getPlayer();

        ColoredAnvils.itemColorTranslator().updateColorTranslationForAnvilOutput(anvilInventory, humanEntity);
    }

    @EventHandler
    public void onAnvilInventoryClick(InventoryClickEvent event) {
        var inventory = event.getInventory();
        if (!(inventory instanceof AnvilInventory)) {
            return;
        }
        var clickedSlotIndex = event.getRawSlot();
        if (clickedSlotIndex != AnvilConstants.OUTPUT_SLOT) {
            return;
        }
        var outputItem = event.getCurrentItem();
        var foundIllegalWords = ColoredAnvils.nameFilter().findIllegalWordsInName(outputItem);
        if (foundIllegalWords.isEmpty()) {
            return;
        }
        var humanEntity = event.getWhoClicked();
        foundIllegalWords.forEach(illegalWord ->
                humanEntity.sendMessage(ChatColor.RED + ColoredAnvils.nameFilter().getFilterMessage() + "'" + ChatColor.BOLD + illegalWord + ChatColor.RED + "'.")
        );
        event.setCancelled(true);
        if (humanEntity instanceof Player) {
            var player = (Player) humanEntity;
            player.setExp(player.getExp()); // Cancelling the event will still visually take the player's EXP - this corrects it
        }
        inventory.setItem(AnvilConstants.OUTPUT_SLOT, new ItemStack(Material.AIR)); // Clients will not see the output slot clear without this
    }

}
