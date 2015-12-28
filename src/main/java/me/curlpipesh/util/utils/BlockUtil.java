package me.curlpipesh.util.utils;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author audrey
 * @since 12/27/15.
 */
public final class BlockUtil {
    private BlockUtil() {
    }

    public static List<Block> getBlocksFromEntity(final LivingEntity entity, final int distance) {
        final List<Block> blocks = new ArrayList<>();
        final Iterator<Block> blockIterator = new BlockIterator(entity, distance);
        while(blockIterator.hasNext()) {
            blocks.add(blockIterator.next());
        }
        return blocks;
    }

    public static List<Block> getBlocksFromEntityFeet(final Entity entity, final int distance) {
        final List<Block> blocks = new ArrayList<>();
        final Iterator<Block> blockIterator = new BlockIterator(entity.getLocation(), 0, distance);
        while(blockIterator.hasNext()) {
            blocks.add(blockIterator.next());
        }
        return blocks;
    }
}
