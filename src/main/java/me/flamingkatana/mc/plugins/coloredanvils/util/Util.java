package me.flamingkatana.mc.plugins.coloredanvils.util;

import me.flamingkatana.mc.plugins.coloredanvils.ColoredAnvils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static Integer getServerVersionAsInt() {
        // Credits: Kikisito
        Pattern n = Pattern.compile("^(\\d)\\.(\\d+)");
        Matcher nm = n.matcher(ColoredAnvils.getPlugin().getServer().getBukkitVersion());
        Integer version = null;
        while (nm.find()) {
            try {
                version = Integer.parseInt(nm.group(2));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return version;
    }

    public static String stripChars(String str, char... chars) {
        String strippedStr = str;
        for (char c : chars) {
            strippedStr = str.replaceAll(String.valueOf(c), "");
        }
        return strippedStr;
    }

    public static String doubleCharacters(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(c);
            sb.append(c);
        }
        return sb.toString();
    }

}
