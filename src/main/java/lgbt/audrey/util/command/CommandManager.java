package lgbt.audrey.util.command;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

/**
 * Command manager that handles registering custom commands with Bukkit.
 *
 * @author audrey
 * @since 12/21/15.
 */
public final class CommandManager {
    @Getter
    private final CommandMap commandMap;

    public CommandManager(@NonNull final Plugin plugin) {
        try {
            final Field field = plugin.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            commandMap = (CommandMap) field.get(plugin.getServer());
        } catch(final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void registerCommand(final Command command) {
        commandMap.register(command.getName(), command.getLabel(), command);
    }
}
