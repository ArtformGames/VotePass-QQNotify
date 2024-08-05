package com.artformgames.plugin.votepass.addon.qqnotify;

import cc.carm.lib.easyplugin.EasyPlugin;
import cc.carm.lib.easyplugin.i18n.EasyPluginMessageProvider;
import cc.carm.lib.easyplugin.updatechecker.GHUpdateChecker;
import cc.carm.lib.mineconfiguration.bukkit.MineConfiguration;
import com.artformgames.plugin.votepass.addon.qqnotify.command.AdminCommands;
import com.artformgames.plugin.votepass.addon.qqnotify.conf.PluginConfig;
import com.artformgames.plugin.votepass.addon.qqnotify.conf.PluginMessages;
import com.artformgames.plugin.votepass.addon.qqnotify.listener.RequestListener;
import org.bstats.bukkit.Metrics;

public class Main extends EasyPlugin {
    private static Main instance;

    public Main() {
        super(EasyPluginMessageProvider.ZH_CN);
        Main.instance = this;
    }

    protected MineConfiguration configuration;

    @Override
    protected void load() {

        log("加载插件配置...");
        this.configuration = new MineConfiguration(this);
        this.configuration.initializeConfig(PluginConfig.class);
        this.configuration.initializeMessage(PluginMessages.class);

    }

    @Override
    protected boolean initialize() {

        log("注册监听器...");
        registerListener(new RequestListener());

        log("注册指令...");
        registerCommand("VotePassQQNotify", new AdminCommands(this));

        if (PluginConfig.METRICS.getNotNull()) {
            log("启用统计数据...");
            new Metrics(this, 21159);
        }

        if (PluginConfig.CHECK_UPDATE.getNotNull()) {
            log("开始检查更新...");
            getScheduler().runAsync(GHUpdateChecker.runner(this));
        } else {
            log("已禁用检查更新，跳过。");
        }

        return true;
    }

    @Override
    public boolean isDebugging() {
        return PluginConfig.DEBUG.getNotNull();
    }

    public MineConfiguration getConfiguration() {
        return configuration;
    }

    public static void info(String... messages) {
        getInstance().log(messages);
    }

    public static void severe(String... messages) {
        getInstance().error(messages);
    }

    public static void debugging(String... messages) {
        getInstance().debug(messages);
    }

    public static Main getInstance() {
        return instance;
    }

}