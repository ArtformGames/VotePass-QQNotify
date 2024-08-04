package com.artformgames.plugin.votepass.addon.qqnotify.command.admin;

import cc.carm.lib.easyplugin.command.SimpleCompleter;
import cc.carm.lib.easyplugin.command.SubCommand;
import com.artformgames.plugin.votepass.addon.qqnotify.command.AdminCommands;
import com.artformgames.plugin.votepass.api.data.request.RequestResult;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class TestCommand extends SubCommand<AdminCommands> {

    public TestCommand(@NotNull AdminCommands parent, String identifier, String... aliases) {
        super(parent, identifier, aliases);
    }

    @Override
    public Void execute(JavaPlugin plugin, CommandSender sender, String[] args) throws Exception {
        if (args.length != 2) return getParent().noArgs(sender);


        return null;
    }

    @Override
    public List<String> tabComplete(JavaPlugin plugin, CommandSender sender, String[] args) {
        if (args.length == 1) {
            return SimpleCompleter.objects(args[0], Arrays.stream(RequestResult.values()).filter(r -> r != RequestResult.PENDING));
        } else return super.tabComplete(plugin, sender, args);
    }

}
