package com.artformgames.plugin.votepass.addon.qqnotify.command.admin;

import cc.carm.lib.easyplugin.command.SimpleCompleter;
import cc.carm.lib.easyplugin.command.SubCommand;
import com.artformgames.plugin.votepass.addon.qqnotify.command.AdminCommands;
import com.artformgames.plugin.votepass.addon.qqnotify.conf.PluginConfig;
import com.artformgames.plugin.votepass.addon.qqnotify.conf.PluginMessages;
import com.artformgames.plugin.votepass.addon.qqnotify.manager.BotHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TestCommand extends SubCommand<AdminCommands> {

    public TestCommand(@NotNull AdminCommands parent, String identifier, String... aliases) {
        super(parent, identifier, aliases);
    }

    @Override
    public Void execute(JavaPlugin plugin, CommandSender sender, String[] args) throws Exception {

        String name = args.length > 0 ? args[0] : sender.getName();
        var message = PluginConfig.NOTIFICATION.NEW_REQUEST.parse(sender, name, "TESTING");
        for (Long group : PluginConfig.BOT.GROUPS) {
            PluginMessages.TEST.START.send(sender, group);
            BotHandler.sendMessage(true, group, message).exceptionally(e -> {
                e.printStackTrace();
                return null;
            });
        }
        return null;
    }

    @Override
    public List<String> tabComplete(JavaPlugin plugin, CommandSender sender, String[] args) {
        if (args.length == 1) {
            return SimpleCompleter.onlinePlayers(args[args.length - 1]);
        } else return super.tabComplete(plugin, sender, args);
    }

}
