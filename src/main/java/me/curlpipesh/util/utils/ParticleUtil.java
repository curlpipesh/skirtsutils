package me.curlpipesh.util.utils;

import net.minecraft.server.v1_8_R2.EnumParticle;
import net.minecraft.server.v1_8_R2.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author audrey
 * @since 12/26/15.
 */
@SuppressWarnings("UnusedParameters")
public final class ParticleUtil {
    public static final int MAX_PARTICLE_DISTANCE = 256;

    private ParticleUtil() {
    }
    
    public static void sendParticleToPlayer(final Player player, final EnumParticle particle) {
        sendParticleToPlayer(player, particle, 1F, 1);
    }

    public static void sendParticleToPlayer(final Player player, final EnumParticle particle, final float speed) {
        sendParticleToPlayer(player, particle, speed, 1);
    }

    public static void sendParticleToPlayer(final Player player, final EnumParticle particle, final int count) {
        sendParticleToPlayer(player, particle, 1F, count);
    }

    public static void sendParticleToPlayer(final Player player, final EnumParticle particle, final float speed,
                                            final int count) {
        PacketUtil.sendPacket(player, new PacketPlayOutWorldParticles(particle, false, (float) player.getLocation().getX(),
                (float) player.getLocation().getY(), (float) player.getLocation().getZ(), 0, 0, 0, speed, count));
    }
    
    public static void sendParticleToLocation(final Location location, final EnumParticle particle) {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.getLocation().distance(location) <= MAX_PARTICLE_DISTANCE)
                .forEach(player -> sendParticleToPlayer(player, particle));
    }
    
    public static void sendParticleToLocation(final Location location, final EnumParticle particle, final float speed) {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.getLocation().distance(location) <= MAX_PARTICLE_DISTANCE)
                .forEach(player -> sendParticleToPlayer(player, particle, speed));
    }
    
    public static void sendParticleToLocation(final Location location, final EnumParticle particle, final int count) {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.getLocation().distance(location) <= MAX_PARTICLE_DISTANCE)
                .forEach(player -> sendParticleToPlayer(player, particle, count));
    }
    
    public static void sendParticleToLocation(final Location location, final EnumParticle particle, final float speed,
                                              final int count) {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.getLocation().distance(location) <= MAX_PARTICLE_DISTANCE)
                .forEach(player -> sendParticleToPlayer(player, particle, speed, count));
    }
}
