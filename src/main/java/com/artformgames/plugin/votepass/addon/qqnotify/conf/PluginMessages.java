package com.artformgames.plugin.votepass.addon.qqnotify.conf;

import cc.carm.lib.configuration.core.Configuration;
import cc.carm.lib.mineconfiguration.bukkit.value.ConfiguredMessageList;

public interface PluginMessages extends Configuration {

    interface COMMAND extends Configuration {

        ConfiguredMessageList<String> USAGE = ConfiguredMessageList.asStrings()
                .defaults(
                        "&e&lVotePass-QQNotify &fAdmin Commands",
                        "&8#&f test &e<result> &e<receiver>",
                        "&8-&7 Test the mail sending.",
                        "&8#&f reload",
                        "&8-&7 Reload the configuration file."
                ).build();

        ConfiguredMessageList<String> NO_PERMISSION = ConfiguredMessageList.asStrings()
                .defaults("&c&lSorry! &fBut you dont have enough permissions to do that!")
                .build();

    }

    interface RELOAD extends Configuration {

        ConfiguredMessageList<String> START = ConfiguredMessageList.asStrings()
                .defaults("&fReloading the plugin configurations...")
                .build();

        ConfiguredMessageList<String> SUCCESS = ConfiguredMessageList.asStrings()
                .defaults("&a&lSuccess! &fThe plugin configurations has been reloaded, cost &a%(time)&fms.")
                .params("time")
                .build();

        ConfiguredMessageList<String> FAILED = ConfiguredMessageList.asStrings()
                .defaults("&c&lFailed! &fThe plugin configurations failed to reload.")
                .build();

    }


    interface TEST extends Configuration {

        ConfiguredMessageList<String> START = ConfiguredMessageList.asStrings()
                .defaults("&f已尝试发送测试消息到 &e%(group) &f，请在群中查看消息是否发送成功。")
                .params("group")
                .build();

    }


}

