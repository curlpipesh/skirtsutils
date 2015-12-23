package me.curlpipesh.util.command;

import lombok.Getter;
import me.curlpipesh.util.chat.MessageUtil;
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
public class SkirtsCommand extends Command {
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
        return executor.onCommand(commandSender, this, commandString, args);
    }


    public static final class Builder {
        private String name = "command";
        private String desc = "Default command description";
        private String usage = "/command";
        private String label = "skirtsplugin";

        private String permissionNode;
        private String permissionMessage = SkirtsPlugin.PREFIX + " " + ChatColor.RED + "You don't have permission to do that!" + ChatColor.RESET;

        private final List<String> aliases = new ArrayList<>();

        private CommandExecutor executor;

        private SkirtsPlugin plugin;

        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            this.usage = "/" + name;
            return this;
        }

        public Builder setDescription(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder setLabel(String label) {
            this.label = label;
            return this;
        }

        public Builder setUsage(String usage) {
            this.usage = usage;
            return this;
        }

        public Builder addAlias(String alias) {
            this.aliases.add(alias);
            return this;
        }

        public Builder setPermissionNode(String permissionNode) {
            this.permissionNode = permissionNode;
            return this;
        }

        public Builder setPermissionMessage(String permissionMessage) {
            this.permissionNode = permissionMessage;
            return this;
        }

        public Builder setExecutor(CommandExecutor executor) {
            this.executor = executor;
            return this;
        }

        public Builder setPlugin(SkirtsPlugin plugin) {
            this.plugin = plugin;
            return this;
        }

        public Command build() {
            if(permissionNode == null || permissionNode.isEmpty()) {
                throw new IllegalStateException("Tried to build a command without a permission node!");
            }
            final SkirtsCommand command = new SkirtsCommand(this.name, this.desc, this.usage, this.aliases);
            command.setPermission(permissionNode);
            command.setPermissionMessage(permissionMessage);
            command.executor = this.executor;
            return command;
        }
    }
}
