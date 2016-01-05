package me.curlpipesh.util;

import me.curlpipesh.util.command.SkirtsCommand;
import me.curlpipesh.util.plugin.SkirtsPlugin;
import me.curlpipesh.util.utils.MessageUtil;

/**
 * @author audrey
 * @since 12/21/15.
 */
public class Util extends SkirtsPlugin {
    @Override
    public void onEnable() {
        getCommandManager().registerCommand(SkirtsCommand.builder()
                .setName("skirtsplugin")
                .setDescription("Used for testing that skirts' plugins work")
                .setUsage("/skirtsplugin")
                .setPermissionNode("skirts.util.debug")
                .setExecutor((commandSender, command, s, strings) -> {
                    if(commandSender.isOp()) {
                        // 0 is dummy argument because ambiguous call
                        MessageUtil.sendMessages(commandSender,
                                SkirtsPlugin.PREFIX, 0, "Plugins work!");
                    }
                    return true;
                }).setPlugin(this).build());
    }
}
