package lgbt.audrey.util;

import lgbt.audrey.util.command.SkirtsCommand;
import lgbt.audrey.util.plugin.SkirtsPlugin;
import lgbt.audrey.util.utils.MessageUtil;

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
