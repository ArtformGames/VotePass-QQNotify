package com.artformgames.plugin.votepass.addon.qqnotify.listener;

import com.artformgames.plugin.votepass.addon.qqnotify.conf.PluginConfig;
import com.artformgames.plugin.votepass.addon.qqnotify.manager.BotHandler;
import com.artformgames.plugin.votepass.lobby.VotePassLobbyAPI;
import com.artformgames.plugin.votepass.lobby.api.data.server.ServerSettings;
import com.artformgames.plugin.votepass.lobby.api.event.RequestCreatedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RequestListener implements Listener {

    @EventHandler
    public void onRequestCreated(RequestCreatedEvent event) {

        var request = event.getRequest();

        ServerSettings serverSettings = VotePassLobbyAPI.getServersManager().getSettings(request.getServer());
        String serverName = serverSettings == null ? request.getServer() : serverSettings.name();

        var message = PluginConfig.NOTIFICATION.NEW_REQUEST.parse(
                null, request.getUser().name(), serverName
        );

        for (Long group : PluginConfig.BOT.GROUPS) {
            BotHandler.sendGroupMessage(group, message).exceptionally(e -> {
                e.printStackTrace();
                return null;
            });
        }
    }

}
