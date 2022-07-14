package moe.lumii.spigot.necessities.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class PlayerUtils {
    private static final String PREFIX = String.format(
            "%s[%sBroHoof%s] %s",
            ChatColor.GRAY,
            ChatColor.GOLD,
            ChatColor.GRAY,
            ChatColor.RESET
    );

    public static void sendMessage(final CommandSender sender, final boolean prefix, final String... messages) {
        if (sender instanceof Player) {
            if (prefix) {
                for (int i = 0; i < messages.length; i++) {
                    messages[i] = String.format("%s%s", PREFIX, messages[i]);
                }
            }
            sender.sendMessage(messages);
        }
    }
}
