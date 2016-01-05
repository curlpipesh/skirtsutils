package me.curlpipesh.util.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Used for sending messages to the server
 *
 * @author audrey
 * @since 12/21/15.
 */
@SuppressWarnings({"unused", "ConstantConditions"})
public final class MessageUtil {
    private MessageUtil() {
    }

    public static void sendMessage(final CommandSender user, final String message) {
        user.sendMessage(message);
    }

    public static void sendMessages(final CommandSender user, final String... messages) {
        user.sendMessage(messages);
    }

    public static void sendMessage(final CommandSender user, final String header, final String message) {
        sendMessage(user, header + ' ' + message);
    }

    public static void sendMessages(final CommandSender user, final String header, final int dummy, final String... messages) {
        sendMessages(user, Arrays.stream(messages).map(m -> header + ChatColor.RESET + ' ' + m).toArray(String[]::new));
    }
}
