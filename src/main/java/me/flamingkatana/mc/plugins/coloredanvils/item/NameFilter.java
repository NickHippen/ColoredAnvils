package me.flamingkatana.mc.plugins.coloredanvils.item;

import me.flamingkatana.mc.plugins.coloredanvils.ColoredAnvils;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NameFilter {

    private final List<String> illegalWords;
    private final String filterMessage;

    public NameFilter() {
        boolean isFilterEnabled = ColoredAnvils.getPlugin().getConfig().getBoolean("Filter_Enabled");
        if (isFilterEnabled) {
            illegalWords = ColoredAnvils.getPlugin().getConfig().getStringList("Filter");
        } else {
            illegalWords = Collections.emptyList();
        }
        filterMessage = ColoredAnvils.getPlugin().getConfig().getString("Filter_Message");
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
                .filter(illegalWord -> containsIgnoreCase(name, illegalWord))
                .collect(Collectors.toList());
    }

    private boolean containsIgnoreCase(String str, String check) {
        return str.toLowerCase().contains(check.toLowerCase());
    }

    public String getFilterMessage() {
        return filterMessage;
    }
}
