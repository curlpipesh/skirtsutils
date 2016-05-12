package lgbt.audrey.util;

import lgbt.audrey.util.command.AudreyCommand;
import lgbt.audrey.util.plugin.AudreyPlugin;
import lgbt.audrey.util.utils.MessageUtil;

/**
 * @author audrey
 * @since 12/21/15.
 */
public class Util extends AudreyPlugin {
    @Override
    public void onEnable() {
        getCommandManager().registerCommand(AudreyCommand.builder()
                .setName("skirtsplugin")
                .setDescription("Used for testing that skirts' plugins work")
                .setUsage("/skirtsplugin")
                .setPermissionNode("skirts.util.debug")
                .setExecutor((commandSender, command, s, strings) -> {
                    if(commandSender.isOp()) {
                        // 0 is dummy argument because ambiguous call
                        MessageUtil.sendMessages(commandSender,
                                AudreyPlugin.PREFIX, 0, "Plugins work!");
                    }
                    return true;
                }).setPlugin(this).build());
    }
}
