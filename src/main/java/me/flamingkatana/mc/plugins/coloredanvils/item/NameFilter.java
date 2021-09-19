package me.flamingkatana.mc.plugins.coloredanvils.item;

import me.flamingkatana.mc.plugins.coloredanvils.ColoredAnvils;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NameFilter {

    private final List<String> illegalWords;

    public NameFilter() {
        boolean isFilterEnabled = ColoredAnvils.getPlugin().getConfig().getBoolean("Filter_Enabled");
        if (isFilterEnabled) {
            illegalWords = ColoredAnvils.getPlugin().getConfig().getStringList("Filter");
        } else {
            illegalWords = Collections.emptyList();
        }
    }

    public List<String> findIllegalWordsInName(ItemStack itemStack) {
        if (itemStack == null) {
            return Collections.emptyList();
        }
        var itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return Collections.emptyList();
        }
        var itemName = itemMeta.getDisplayName();
        return findIllegalWords(itemName);
    }

    public List<String> findIllegalWords(String name) {
        return illegalWords.stream()
                .filter(name::contains)
                .collect(Collectors.toList());
    }

}