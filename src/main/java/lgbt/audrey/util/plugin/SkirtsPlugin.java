package lgbt.audrey.util.plugin;

import lgbt.audrey.util.command.CommandManager;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Ye olde Special Snowflake Plugin (R)(C)(TM)(ETC). You <b>MUST</b> call
 * <code>suprt()</code> in your plugin's constructor in order for custom
 * command registration to work!
 *
 * TODO: Actually make this a special snowflake
 *
 * @author audrey
 * @since 12/21/15.
 */
public abstract class SkirtsPlugin extends JavaPlugin {
    /**
     * The {@link CommandManager} instance for this plugin. Used for
     * registering custom commands with Bukkit.
     */
    @Getter
    private final CommandManager commandManager;

    /**
     * SkirtsPlugin prefix.
     */
    public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.RED + "SkirtsPlugin" + ChatColor.GRAY +
            ']' + ChatColor.RESET;

    public SkirtsPlugin() {
        commandManager = new CommandManager(this);
    }
}
