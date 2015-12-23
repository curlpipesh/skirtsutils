package me.curlpipesh.util.chat;

import me.curlpipesh.util.network.PacketUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Used for sending messages to the server
 *
 * @author audrey
 * @since 12/21/15.
 */
@SuppressWarnings({"unused", "ConstantConditions"})
public final class MessageUtil {
    public static void sendMessage(CommandSender user, String message) {
        user.sendMessage(message);
    }

    public static void sendMessages(CommandSender user, String... messages) {
        user.sendMessage(messages);
    }

    public static void sendMessage(CommandSender user, String header, String message) {
        sendMessage(user, header + " " + message);
    }

    public static void sendMessages(CommandSender user, String header, int dummy, String... messages) {
        sendMessages(user, Arrays.stream(messages).map(m -> header + ChatColor.RESET + " " + m).toArray(String[]::new));
    }

    public static void sendTitle(final Player player, final int fadeIn, final int stay, final int fadeOut, String title, String subtitle) {
        try {
            sendTitle(player, fadeIn, stay, fadeOut, title, false);
            sendTitle(player, fadeIn, stay, fadeOut, subtitle, true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String message, boolean isSubtitle) throws Exception {
        message = ChatColor.translateAlternateColorCodes('&', message);
        message = message.replaceAll("%player%", player.getDisplayName());
        final Object enumSubtitle = PacketUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0]
                .getField(isSubtitle ? "SUBTITLE" : "TITLE").get(null);
        final Object chatSubtitle = PacketUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
                .getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}");
        final Constructor<?> subtitleConstructor = PacketUtil.getNMSClass("PacketPlayOutTitle")
                .getConstructor(PacketUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], 
                        PacketUtil.getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
        final Object subtitlePacket = subtitleConstructor.newInstance(enumSubtitle, chatSubtitle, fadeIn, stay, fadeOut);
        PacketUtil.sendPacket(player, subtitlePacket);
    }
    
    public static void sendTabTitle(final Player player, String header, String footer) {
        if(header == null) {
            header = "";
        }
        header = ChatColor.translateAlternateColorCodes('&', header);
        if(footer == null) {
            footer = "";
        }
        footer = ChatColor.translateAlternateColorCodes('&', footer);
        header = header.replaceAll("%player%", player.getDisplayName());
        footer = footer.replaceAll("%player%", player.getDisplayName());
        try {
            final Object tabHeader = PacketUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}");
            final Object tabFooter = PacketUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}");
            final Constructor<?> titleConstructor = PacketUtil.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(PacketUtil.getNMSClass("IChatBaseComponent"));
            final Object packet = titleConstructor.newInstance(tabHeader);
            final Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, tabFooter);
            PacketUtil.sendPacket(player, packet);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
