package com.oveln.ovbot.listener

import com.oveln.ovbot.Main
import com.oveln.ovbot.data.Config
import com.oveln.ovbot.data.Data
import com.oveln.ovbot.message.Message
import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.bukkit.event.MiraiGroupMessageEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener


class GroupMessageRecevice: Listener {
    @EventHandler
    fun onListened(event: MiraiGroupMessageEvent) {
        val message = event.messageContent
        if (Config.OnlinePlayer.Check(message)) {
            MiraiBot.getBot(event.botID).getGroup(event.groupID).sendMessage(
                Config.OnlinePlayer.GetMessage().replace("%players%" , Bukkit.getServer().onlinePlayers.size.toString())
            )
        }
        if (message[0] == '#') {
            val cmd = message.split(" ")
            if (cmd[0] == "#白名单" && cmd.size == 2) {
                if (!Data.whitelist.containsKey(event.senderID)) {
                    MiraiBot.getBot(event.botID).getGroup(event.groupID).sendMessage(
                        Config.WhiteListAddMessage.GetMessage()
                            .replace("%sender%" , event.senderNameCard)
                            .replace("%name%" , cmd[1])
                    )
                    Data.whitelist[event.senderID] = cmd[1]
                } else {
                    MiraiBot.getBot(event.botID).getGroup(event.groupID).sendMessage(
                        Config.WhiteListChangeMessage.GetMessage()
                            .replace("%sender%" , event.senderNameCard)
                            .replace("%name%" , cmd[1])
                            .replace("%old_name%",Data.whitelist[event.senderID]?:" ")
                    )
                    Data.whitelist[event.senderID] = cmd[1]
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