package com.oveln.ovbot.listener

import com.oveln.ovbot.Main
import com.oveln.ovbot.data.Config
import com.oveln.ovbot.data.Data
import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.bukkit.event.MiraiGroupMessageEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener


class GroupMessageRecevice: Listener {
    @EventHandler
    fun onListened(event: MiraiGroupMessageEvent) {
        val message = event.messageContent
        if (message.contains("在线人数")) {
            MiraiBot.getBot(event.botID).getGroup(event.groupID).sendMessage(
                "宝，现在有${Bukkit.getServer().onlinePlayers.size}个玩家在线"
            )
        }
        if (message[0] == '#') {
            val cmd = message.split(" ")
            if (cmd[0] == "#白名单" && cmd.size == 2) {
                if (!Data.whitelist.containsKey(event.senderID)) {
                    MiraiBot.getBot(event.botID).getGroup(event.groupID).sendMessage("已经为您添加白名单:${cmd[1]}")
                    Data.whitelist[event.senderID] = cmd[1]
                    Main.Instance.logger.info("已经为您添加白名单:${cmd[1]}")
                } else {
                    MiraiBot.getBot(event.botID).getGroup(event.groupID).sendMessage("已经为您修改白名单:${Data.whitelist[event.senderID]}->${cmd[1]}")
                    Data.whitelist[event.senderID] = cmd[1]
                    Main.Instance.logger.info("已经为您修改白名单:${Data.whitelist[event.senderID]}->${cmd[1]}")
                }
            }
            if (message == "#资料") {
                MiraiBot.getBot(event.botID).getGroup(event.groupID).sendMessage(Data.getPlayerData(event.senderID))
            }
            if (message == "#帮助") {
                MiraiBot.getBot(event.botID).getGroup(event.groupID).sendMessage(Config.HelpMessage)
            }
        }
    }
}