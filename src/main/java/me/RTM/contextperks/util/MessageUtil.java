package me.RTM.contextperks.util;

import net.md_5.bungee.api.ChatColor;

public class MessageUtil {

    public static final String PREFIX =
            ChatColor.of("#00D4FF") + "ContextPerks "
                    + ChatColor.DARK_GRAY + "» "
                    + ChatColor.GRAY;

    public static String success(String message) {
        return PREFIX + ChatColor.GREEN + message;
    }

    public static String error(String message) {
        return PREFIX + ChatColor.RED + message;
    }

    public static String info(String message) {
        return PREFIX + ChatColor.AQUA + message;
    }
}
