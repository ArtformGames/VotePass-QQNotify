package com.artformgames.plugin.votepass.addon.qqnotify.conf;

import cc.carm.lib.configuration.core.Configuration;
import cc.carm.lib.configuration.core.annotation.HeaderComment;
import cc.carm.lib.configuration.core.value.type.ConfiguredList;
import cc.carm.lib.configuration.core.value.type.ConfiguredValue;
import cc.carm.lib.mineconfiguration.bukkit.value.ConfiguredMessageList;

import java.time.format.DateTimeFormatter;

public interface PluginConfig extends Configuration {

    DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    ConfiguredValue<Boolean> DEBUG = ConfiguredValue.of(Boolean.class, false);

    @HeaderComment({
            "统计数据设定",
            " 该选项用于帮助开发者统计插件版本与使用情况，且绝不会影响性能与使用体验。",
            " 当然，您也可以选择在这里关闭，或在plugins/bStats下的配置文件中关闭。"
    })
    ConfiguredValue<Boolean> METRICS = ConfiguredValue.of(Boolean.class, true);

    @HeaderComment({
            "检查更新设定",
            "该选项用于插件判断是否要检查更新，若您不希望插件检查更新并提示您，可以选择关闭。",
            "检查更新为异步操作，绝不会影响性能与使用体验。"
    })
    ConfiguredValue<Boolean> CHECK_UPDATE = ConfiguredValue.of(Boolean.class, true);


    @HeaderComment({"OneBot 机器人配置，请确保启用 HTTP 功能。"})
    interface BOT extends Configuration {

        @HeaderComment("OneBot 机器人的 HTTP API 请求地址")
        ConfiguredValue<String> URL = ConfiguredValue.of("http://localhost:5700/");

        @HeaderComment("通知的群号列表，用于发送通知。请确保机器人在这些群中。")
        ConfiguredList<Long> GROUPS = ConfiguredList.builderOf(Long.class).fromString()
                .parseValue(Long::parseUnsignedLong).serializeValue(String::valueOf)
                .defaults(123456789L)
                .build();

        @HeaderComment("OneBot 的验证设定")
        interface AUTHORIZATION extends Configuration {
            ConfiguredValue<Boolean> ENABLED = ConfiguredValue.of(true);
            ConfiguredValue<String> ACCESS_TOKEN = ConfiguredValue.of("YOUR_ACCESS_TOKEN");
        }

    }

    @HeaderComment("通知设定")
    interface NOTIFICATION extends Configuration {

        ConfiguredMessageList<String> NEW_REQUEST = ConfiguredMessageList.asStrings()
                .defaults(
                        "新玩家 %(player) 刚刚提交了对 %(server) 服务器的白名单请求，",
                        "赶快加入服务器参与白名单审核投票叭~"
                ).params("player", "server")
                .build();

    }


}