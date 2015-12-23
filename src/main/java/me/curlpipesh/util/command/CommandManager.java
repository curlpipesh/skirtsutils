package me.curlpipesh.util.command;

import lombok.Getter;
import lombok.NonNull;
import me.curlpipesh.util.plugin.SkirtsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

/**
 * @author audrey
 * @since 12/21/15.
 */
public final class CommandManager {
    private SkirtsPlugin plugin;

    @Getter
    private CommandMap commandMap;

    public CommandManager(@NonNull SkirtsPlugin plugin) {
        try {
            Field field = plugin.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            commandMap = (CommandMap) field.get(plugin.getServer());
        } catch(Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void registerCommand(Command command) {
        commandMap.register(command.getName(), command.getLabel(), command);
    }
}
