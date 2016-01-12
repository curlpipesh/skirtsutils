package me.curlpipesh.util.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Various entity-related utilities
 *
 * @author audrey
 * @since 12/27/15.
 */
@SuppressWarnings("unused")
public final class EntityUtil {
    private EntityUtil() {
    }

    /**
     * Gets the entity that a player is looking at within the given range.
     *
     * Author: Some guy on the Bukkit forums
     * See also: <a href="https://bukkit.org/threads/getting-entity-player-is-looking-at.262921/#post-2453991">This link</a>
     * @param player The player to use
     * @param range The maximum distance to find an entity at
     * @return The entity found, or null if no entity.
     */
    public static LivingEntity getTarget(final Player player, final int range) {
        final List<Entity> nearbyE = player.getNearbyEntities(range, range, range);
        final Collection<LivingEntity> livingE = nearbyE.stream()
                .filter(e -> e instanceof LivingEntity).map(e -> (LivingEntity) e)
                .collect(Collectors.toCollection(ArrayList::new));

        LivingEntity target = null;
        final Iterator<Block> bItr = new BlockIterator(player, range);
        Block block;
        Location loc;
        int bx;
        int by;
        int bz;
        double ex;
        double ey;
        double ez;
        // loop through player's line of sight
        while(bItr.hasNext()) {
            block = bItr.next();
            bx = block.getX();
            by = block.getY();
            bz = block.getZ();
            // check for entities near this block in the line of sight
            for(final LivingEntity e : livingE) {
                loc = e.getLocation();
                ex = loc.getX();
                ey = loc.getY();
                ez = loc.getZ();
                if(bx - .75 <= ex && ex <= bx + 1.75
                        && bz - .75 <= ez && ez <= bz + 1.75
                        && by - 1 <= ey && ey <= by + 2.5) {
                        // entity is close enough, set target and stop
                    target = e;
                    break;
                }
            }
        }
        return target;
    }
}
