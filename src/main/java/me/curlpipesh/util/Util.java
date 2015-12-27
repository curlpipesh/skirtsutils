package me.curlpipesh.util;

import me.curlpipesh.util.chat.MessageUtil;
import me.curlpipesh.util.command.SkirtsCommand;
import me.curlpipesh.util.plugin.SkirtsPlugin;
import org.bukkit.entity.Player;

/**
 * @author audrey
 * @since 12/21/15.
 */
public class Util extends SkirtsPlugin {
    @Override
    public void onEnable() {
        this.getCommandManager().registerCommand(SkirtsCommand.builder()
                .setName("skirtsplugin")
                .setDescription("Used for testing that skirts' plugins work")
                .setUsage("/skirtsplugin")
                .setPermissionNode("skirts.util.debug")
                .setExecutor((commandSender, command, s, strings) -> {
                    if(commandSender.isOp()) {
                        // 0 is dummy argument because ambiguous call
                        MessageUtil.sendMessages(commandSender,
                                SkirtsPlugin.PREFIX, 0, "Plugins work!");
                        if(commandSender instanceof Player) {
                            MessageUtil.sendTitle((Player) commandSender, 1, 3, 1, "&7YOU ARE &c%player%&7!", "&7This is a &cTEST&7!");
                        }
                    }
                    return true;
                }).setPlugin(this).build());
    }
}
