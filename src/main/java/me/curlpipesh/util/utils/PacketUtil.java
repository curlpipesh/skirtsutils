package me.curlpipesh.util.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author audrey
 * @since 12/22/15.
 */
public class PacketUtil {
    public static void sendPacket(final Player player, final Object packet) {
        try {
            final Object handle = player.getClass().getMethod("getHandle", (Class<?>[]) new Class[0]).invoke(player);
            final Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Class<?> getNMSClass(final String name) {
        final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
