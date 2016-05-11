package lgbt.audrey.util.utils;

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

    /**
     * Send a message to the given user
     *
     * @param user The user to message
     * @param message The message to send
     */
    public static void sendMessage(final CommandSender user, final String message) {
        user.sendMessage(message);
    }

    /**
     * Send a series of messages to a user
     *
     * @param user The user to message
     * @param messages The messages to send
     */
    public static void sendMessages(final CommandSender user, final String... messages) {
        user.sendMessage(messages);
    }

    /**
     * Send a message to the specified player with the given prefix
     *
     * @param user The user to message
     * @param prefix The prefix to use
     * @param message The message to send
     */
    public static void sendMessage(final CommandSender user, final String prefix, final String message) {
        sendMessage(user, prefix + ' ' + message);
    }

    /**
     * Send a series of messages to the given user
     *
     * @param user The user to message
     * @param prefix The prefix to use
     * @param dummy Dummy parameter to differentiate this method from
     *              {@link #sendMessage(CommandSender, String, String)}. '0' is
     *              slightly shorter to type than 'true' or 'false,' hence why
     *              it's an int.
     * @param messages The messages to send
     */
    public static void sendMessages(final CommandSender user, final String prefix, final int dummy, final String... messages) {
        sendMessages(user, Arrays.stream(messages).map(m -> prefix + ChatColor.RESET + ' ' + m).toArray(String[]::new));
    }
}
