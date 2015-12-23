package me.curlpipesh.util.plugin;

import lombok.Getter;
import me.curlpipesh.util.command.CommandManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Ye olde Special Snowflake Plugin (R)(C)(TM)(ETC)
 *
 * TODO: Actually make this a special snowflake
 *
 * @author audrey
 * @since 12/21/15.
 */
public abstract class SkirtsPlugin extends JavaPlugin {
    @Getter
    private CommandManager commandManager;

    public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.RED + "SkirtsPlugin" + ChatColor.GRAY + "]" + ChatColor.RESET;

    public SkirtsPlugin() {
        super();
        this.commandManager = new CommandManager(this);
    }
}
