package me.curlpipesh.util.command;

import lombok.Getter;
import lombok.NonNull;
import me.curlpipesh.util.command.argument.IFlag;
import me.curlpipesh.util.plugin.SkirtsPlugin;
import me.curlpipesh.util.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Custom command class that allows for registering commands without touching
 * plugin.yml.
 *
 * @author audrey
 * @since 12/21/15.
 */
@SuppressWarnings("unused")
public final class SkirtsCommand extends Command {
    /**
     * The executor for this plugin. May not be null.
     */
    private CommandExecutor executor;

    /**
     * The plugin that registered this command. May not be null.
     */
    @Getter
    private SkirtsPlugin plugin;

    @Getter
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final Collection<IFlag> flags;

    private SkirtsCommand(final String name, final String description, final String usageMessage, final List<String> aliases) {
        super(name, description, usageMessage, aliases);

        flags = new ArrayList<>();
    }

    @Override
    public boolean execute(final CommandSender commandSender, final String commandString, final String[] args) {
        if(!commandSender.hasPermission(getPermission())) {
            MessageUtil.sendMessage(commandSender, getPermissionMessage());
            return true;
        }
        final boolean retVal = executor.onCommand(commandSender, this, commandString, args);
        if(!retVal) {
            MessageUtil.sendMessage(commandSender, getUsage());
        }
        return retVal;
    }

    public Map<IFlag, String> parseFlagsAndValues(final String commandString, final String[] args) {
        // TODO
        throw new IllegalStateException("SkirtsCommand#parseFlagsAndValues(String, String[]) isn't implemented yet!");
    }

    /**
     * Returns a new {@link Builder} instance for building commands.
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class used for constructing commands. Validates input to help
     * prevent invalid command registration.
     */
    @SuppressWarnings("unused")
    public static final class Builder {
        /**
         * The name of this command.
         */
        private String name = "";

        /**
         * The description of this command.
         */
        private String desc = "Default command description";

        /**
         * The usage for this command.
         */
        private String usage = "/";

        /**
         * The label for this command. A label is what goes before the ':'
         * character when completing commands that way, such as
         * <code>/essentials:ban</code>.
         */
        private String label = "skirtsplugin";

        /**
         * The permission node for this plugin. May not be null.
         */
        private String permissionNode;

        /**
         * The "You don't have permission!" message
         */
        private String permissionMessage = SkirtsPlugin.PREFIX + ' ' + ChatColor.RED + "You don't have permission to do that!" + ChatColor.RESET;

        /**
         * All aliases for this command
         */
        private final List<String> aliases = new ArrayList<>();

        /**
         * The {@link CommandExecutor} that will be run when this command is
         * run.
         */
        private CommandExecutor executor;

        /**
         * The plugin that registered this command.
         */
        private SkirtsPlugin plugin;

        /**
         * The list of UNIX-like flags that this command takes. Executors are
         * expected to deal with flags on their own, although the flags and
         * values themselves will be parsed out for implementations.
         */
        private final Collection<IFlag> flags = new ArrayList<>();

        private Builder() {
        }
    
        /**
         * Sets the command's name
         *
         * @param name The command's name
         * @return Itself
         */
        public Builder setName(@NonNull final String name) {
            this.name = name;
            usage = '/' + name;
            return this;
        }
    
        /**
         * Sets the command's description.
         *
         * @param desc The command's description.
         * @return Itself
         */
        public Builder setDescription(@NonNull final String desc) {
            this.desc = desc;
            return this;
        }
    
        /**
         * Sets the label for this command. A label is what goes before the ':'
         * character when completing commands that way, such as
         * <code>/essentials:ban</code>.
         *
         * @param label The label to use
         * @return Itself
         */
        public Builder setLabel(@NonNull final String label) {
            this.label = label;
            return this;
        }
    
        /**
         * Sets the usage for this command
         *
         * @param usage The command's usage string
         * @return Itself
         */
        public Builder setUsage(@NonNull final String usage) {
            this.usage = usage;
            return this;
        }
    
        /**
         * Adds an alias for this command
         *
         * @param alias The alias to add
         * @return Itself
         */
        public Builder addAlias(@NonNull final String alias) {
            aliases.add(alias);
            return this;
        }
    
        /**
         * Sets the permission node for this command. Must be called
         *
         * @param permissionNode The permission node to use
         * @return Itself
         */
        public Builder setPermissionNode(@NonNull final String permissionNode) {
            this.permissionNode = permissionNode;
            return this;
        }
    
        /**
         * Sets the no-perms message.
         * 
         * @param permissionMessage The no-perms message
         * @return Itself
         */
        public Builder setPermissionMessage(@NonNull final String permissionMessage) {
            this.permissionMessage = permissionMessage;
            return this;
        }
    
        /**
         * Sets the {@link CommandExecutor} for this command. Must be called
         *
         * @param executor The CommandExecutor to use
         * @return Itself
         */
        public Builder setExecutor(@NonNull final CommandExecutor executor) {
            this.executor = executor;
            return this;
        }
    
        /**
         * Sets the plugin that registered this command
         * 
         * @param plugin The plugin that registered this command
         * @return Itself
         */
        public Builder setPlugin(@NonNull final SkirtsPlugin plugin) {
            this.plugin = plugin;
            return this;
        }

        public Builder addFlag(@NonNull final IFlag flag) {
            flags.add(flag);
            return this;
        }
    
        /**
         * Actually builds the command and returns the instance.
         * 
         * @return An instance of {@link SkirtsCommand} with the specified
         *         properties.
         */
        public Command build() {
            if(permissionNode == null || permissionNode.isEmpty()) {
                throw new IllegalStateException("Tried to build a command without a permission node!");
            }
            if(plugin == null) {
                throw new IllegalStateException("Tried to build a command without a plugin!");
            }
            if(name.isEmpty()) {
                throw new IllegalStateException("Tried to build a nameless command!");
            }
            final SkirtsCommand command = new SkirtsCommand(name, desc, usage, aliases);
            command.setPermission(permissionNode);
            command.setPermissionMessage(permissionMessage);
            command.executor = executor;
            command.flags.addAll(flags);
            return command;
        }
    }
}
