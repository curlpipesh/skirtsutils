package lgbt.audrey.util.utils;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Various block-related utilities
 *
 * @author audrey
 * @since 12/27/15.
 */
@SuppressWarnings("unused")
public final class BlockUtil {
    private BlockUtil() {
    }

    /**
     * Gets all blocks from the given entity up to the given distance, in a
     * straight line.
     *
     * @param entity The entity to use
     * @param distance Max distance to iterate
     * @return The blocks from the entity up to the given distance
     */
    public static List<Block> getBlocksFromEntity(final LivingEntity entity, final int distance) {
        final List<Block> blocks = new ArrayList<>();
        final Iterator<Block> blockIterator = new BlockIterator(entity, distance);
        while(blockIterator.hasNext()) {
            blocks.add(blockIterator.next());
        }
        return blocks;
    }

    /**
     * Like {@link #getBlocksFromEntity(LivingEntity, int)}, but from the
     * entity's feet.
     *
     * @param entity The entity to use
     * @param distance Max distance to iterate
     * @return The blocks from the entity's feet up to the given distance
     */
    public static List<Block> getBlocksFromEntityFeet(final Entity entity, final int distance) {
        final List<Block> blocks = new ArrayList<>();
        final Iterator<Block> blockIterator = new BlockIterator(entity.getLocation(), 0, distance);
        while(blockIterator.hasNext()) {
            blocks.add(blockIterator.next());
        }
        return blocks;
    }
}
