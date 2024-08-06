package com.artformgames.plugin.votepass.addon.qqnotify.manager;

import com.artformgames.plugin.votepass.addon.qqnotify.Main;
import com.artformgames.plugin.votepass.addon.qqnotify.conf.PluginConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BotHandler {

    public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(2))
            .build();

    public static @NotNull String urlOf(@NotNull String action) {
        String url = PluginConfig.BOT.URL.getNotNull();
        return (url.endsWith("/") ? url + action : url + "/" + action).trim();
    }

    public static CompletableFuture<Boolean> sendGroupMessage(long group, List<String> messages) {
        return request("send_group_msg", map -> {
            map.put("group_id", group);
            map.put("message", String.join("\n", messages));
            map.put("auto_escape", true);
        }).thenApply(response -> response.get("status").getAsInt() == 200);
    }

    public static CompletableFuture<Boolean> sendPrivateMessage(long user, List<String> messages) {
        return request("send_private_msg", map -> {
            map.put("user_id", user);
            map.put("message", String.join("\n", messages));
            map.put("auto_escape", true);
        }).thenApply(response -> response.get("status").getAsInt() == 200);
    }

    public static CompletableFuture<Boolean> sendMessage(boolean group, long id, List<String> messages) {
        return request("send_msg", map -> {
            if (group) {
                map.put("message_type", "group");
                map.put("group_id", id);
            } else {
                map.put("message_type", "private");
                map.put("user_id", id);
            }
            map.put("message", String.join("\n", messages));
            map.put("auto_escape", true);
        }).thenApply(response -> response.get("status").getAsString().equals("ok"));
    }

    private static CompletableFuture<JsonObject> request(@NotNull String action,
                                                         @NotNull Consumer<Map<String, Object>> parameters) {
        Map<String, Object> values = new LinkedHashMap<>();
        parameters.accept(values);

        var request = HttpRequest.newBuilder();
        request.uri(URI.create(urlOf(action)));
        request.header("Content-Type", "application/json;charset=UTF-8");

        if (PluginConfig.BOT.AUTHORIZATION.ENABLED.getNotNull()) {   // ACCESS TOKEN AUTH  - Bearer Token
            String token = PluginConfig.BOT.AUTHORIZATION.ACCESS_TOKEN.getNotNull();
            request.header("Authorization", "Bearer " + token);
        }

        String content = GSON.toJson(values);
        Main.debugging(action + " -> " + content);

        request.POST(HttpRequest.BodyPublishers.ofString(content));

        return CLIENT.sendAsync(request.build(), HttpResponse.BodyHandlers.ofString()).thenApply(response -> {
            String body = response.body();
            Main.debugging("[" + response.statusCode() + "] -> " + body);

            if (response.statusCode() != 200) {
                throw new RuntimeException("在请求(" + action + ")时出现错误: " + "[" + response.statusCode() + "]" + body);
            }

            return JsonParser.parseString(body).getAsJsonObject();
        });
    }


}
