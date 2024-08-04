# VotePass-QQNotify

为 **VotePass-Game** 支持**QQ群通知**，基于 [Mirai](https://docs.mirai.mamoe.net/) 机器人实现。

## 项目依赖

关于开发过程中使用的所有依赖项，请移步 [Dependencies](https://github.com/ArtformGames/VotePass-QQNotify/network/dependencies) 。

### 插件依赖

- **[必须]** 插件主体基于 [VotePass-Game](https://github.com/ArtformGames/VotePass/) 开发。
- **[推荐]** 变量部分基于 [PlaceholderAPI](https://www.spigotmc.org/resources/6245/) 开发。

### 机器人依赖

- **[必须]** 机器人功能基于 [Mirai](https://docs.mirai.mamoe.net/) 实现。
- **[必须]** 消息联动基于 [mirai-api-http](https://github.com/project-mirai/mirai-api-http) 插件实现。

## 指令

插件主指令为 `/VotePassQQNotify`.

```text

# test [请求人ID]
@ 管理员指令
- 发送一个新请求等待投票的测试消息。

# reload
@ 管理员指令
- 重载配置文件。

```

## 配置文件

### 基础配置文件 ([`config.yml`](src/main/java/cc/carm/plugin/userprefix/conf/PluginConfig.java))

将会在服务器首次启动时生成，如果您想要修改配置，请在服务器启动后打开配置文件。

### 消息配置文件 ([`messages.yml`](src/main/java/cc/carm/plugin/userprefix/conf/PluginMessages.java))

将会在服务器首次启动时生成，如果您想要修改配置，请在服务器启动后打开配置文件 。

## 插件权限

```text
# VotePass.admin
- 插件管理员权限，用于执行插件的管理指令。
```

## 使用统计

[![bStats](https://bstats.org/signatures/bukkit/VotePass-QQNotify.svg)](https://bstats.org/plugin/bukkit/VotePass-QQNotify/22889)


## 开源协议

本项目源码采用 [GNU General Public License v3.0](https://opensource.org/licenses/GPL-3.0) 开源协议。

<details>
  <summary>关于 GPL 协议</summary>

> GNU General Public Licence (GPL) 有可能是开源界最常用的许可模式。GPL 保证了所有开发者的权利，同时为使用者提供了足够的复制，分发，修改的权利：
>
> #### 可自由复制
> 你可以将软件复制到你的电脑，你客户的电脑，或者任何地方。复制份数没有任何限制。
> #### 可自由分发
> 在你的网站提供下载，拷贝到U盘送人，或者将源代码打印出来从窗户扔出去（环保起见，请别这样做）。
> #### 可以用来盈利
> 你可以在分发软件的时候收费，但你必须在收费前向你的客户提供该软件的 GNU GPL 许可协议，以便让他们知道，他们可以从别的渠道免费得到这份软件，以及你收费的理由。
> #### 可自由修改
> 如果你想添加或删除某个功能，没问题，如果你想在别的项目中使用部分代码，也没问题，唯一的要求是，使用了这段代码的项目也必须使用 GPL 协议。
>
> 需要注意的是，分发的时候，需要明确提供源代码和二进制文件，另外，用于某些程序的某些协议有一些问题和限制，你可以看一下 @PierreJoye 写的 Practical Guide to GPL Compliance 一文。使用 GPL
> 协议，你必须在源代码代码中包含相应信息，以及协议本身。
>
> *以上文字来自 [五种开源协议GPL,LGPL,BSD,MIT,Apache](https://www.oschina.net/question/54100_9455) 。*
</details>

## 支持与致谢

本项目由 [Artfrom Games](https://github.com/ArtformGames/) 团队提供长期支持与维护。

<img src="https://raw.githubusercontent.com/ArtformGames/.github/master/logo/1000pxh/COMPLETE_Reverse_Color.png" width="50%"  height="50%" alt="ArtformGames Logo">

Many thanks to Jetbrains for kindly providing a license for us to work on this and other open-source projects.

[![](https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg)](https://www.jetbrains.com/?from=https://github.com/ArtformGames/VotePass-QQNotify)

