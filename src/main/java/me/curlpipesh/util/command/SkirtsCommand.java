package me.curlpipesh.util.command;

import lombok.Getter;
import me.curlpipesh.util.utils.MessageUtil;
import me.curlpipesh.util.plugin.SkirtsPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * @author audrey
 * @since 12/21/15.
 */
public final class SkirtsCommand extends Command {
    private CommandExecutor executor;

    @Getter
    private SkirtsPlugin plugin;

    private SkirtsCommand(final String name, final String description, final String usageMessage, final List<String> aliases) {
        super(name, description, usageMessage, aliases);
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

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("unused")
    public static final class Builder {
        private String name = "command";
        private String desc = "Default command description";
        private String usage = "/command";
        private String label = "skirtsplugin";

        private String permissionNode;
        private String permissionMessage = SkirtsPlugin.PREFIX + ' ' + ChatColor.RED + "You don't have permission to do that!" + ChatColor.RESET;

        private final List<String> aliases = new ArrayList<>();

        private CommandExecutor executor;

        private SkirtsPlugin plugin;

        private Builder() {
        }

        public Builder setName(final String name) {
            this.name = name;
            usage = '/' + name;
            return this;
        }

        public Builder setDescription(final String desc) {
            this.desc = desc;
            return this;
        }

        public Builder setLabel(final String label) {
            this.label = label;
            return this;
        }

        public Builder setUsage(final String usage) {
            this.usage = usage;
            return this;
        }

        public Builder addAlias(final String alias) {
            aliases.add(alias);
            return this;
        }

        public Builder setPermissionNode(final String permissionNode) {
            this.permissionNode = permissionNode;
            return this;
        }

        public Builder setPermissionMessage(final String permissionMessage) {
            this.permissionMessage = permissionMessage;
            return this;
        }

        public Builder setExecutor(final CommandExecutor executor) {
            this.executor = executor;
            return this;
        }

        public Builder setPlugin(final SkirtsPlugin plugin) {
            this.plugin = plugin;
            return this;
        }

        public Command build() {
            if(permissionNode == null || permissionNode.isEmpty()) {
                throw new IllegalStateException("Tried to build a command without a permission node!");
            }
            final SkirtsCommand command = new SkirtsCommand(name, desc, usage, aliases);
            command.setPermission(permissionNode);
            command.setPermissionMessage(permissionMessage);
            command.executor = executor;
            return command;
        }
    }
}
