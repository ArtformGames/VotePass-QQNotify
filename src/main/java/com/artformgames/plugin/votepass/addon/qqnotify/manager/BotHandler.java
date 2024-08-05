package com.artformgames.plugin.votepass.addon.qqnotify.manager;

import com.artformgames.plugin.votepass.addon.qqnotify.conf.PluginConfig;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BotHandler {

    public static @NotNull String urlOf(@NotNull String action) {
        String url = PluginConfig.BOT.URL.getNotNull();
        return url.endsWith("/") ? url + action : url + "/" + action;
    }

    public static CompletableFuture<Boolean> sendGroupMessage(long group, List<String> messages) {
        return request("send_group_msg", map -> {
            map.put("group_id", group);
            map.put("message", String.join("\n", messages));
            map.put("auto_escape", true);
        }).thenApply(response -> response.get("status").getAsInt() == 200);
    }

    private static CompletableFuture<JsonObject> request(String action, Consumer<Map<String, Object>> parameters) {
        Map<String, Object> values = new LinkedHashMap<>();
        parameters.accept(values);
        return CompletableFuture.supplyAsync(() -> {
            String response = postJSON(urlOf(action), values);
            if (response == null) throw new RuntimeException("Failed to send request to " + action);
            return JsonParser.parseString(response).getAsJsonObject();
        });
    }

    private static String postJSON(String url, Map<String, Object> parameters) {
        try {
            HttpURLConnection connection = getPostConnection(url);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(new Gson().toJson(parameters).getBytes(StandardCharsets.UTF_8));
            }

            try (InputStream input = connection.getInputStream()) {
                return new String(input.readAllBytes(), StandardCharsets.UTF_8);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static @NotNull HttpURLConnection getPostConnection(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        if (PluginConfig.BOT.AUTHORIZATION.ENABLED.getNotNull()) {
            connection.setRequestProperty(
                    "Authorization",
                    "Bearer " + PluginConfig.BOT.AUTHORIZATION.ACCESS_TOKEN.getNotNull()
            );
        }
        return connection;
    }

}
