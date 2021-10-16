package com.oveln.ovbot.listener

import com.oveln.ovbot.Main
import com.oveln.ovbot.data.Config
import com.oveln.ovbot.data.Data
import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.bukkit.event.MiraiFriendMessageEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class FriendMessageReceive: Listener {
    @EventHandler
    fun onListened(event: MiraiFriendMessageEvent) {
        val message = event.messageContent
        if (Config.OnlinePlayer.Check(message)) {
            MiraiBot.getBot(event.botID).getFriend(event.senderID).sendMessage(
                Config.OnlinePlayer.GetMessage().replace("%players%" , Bukkit.getServer().onlinePlayers.size.toString())
            )
        }
        if (message[0] == '#') {
            val cmd = message.split(" ")
            if (cmd[0] == "#白名单" && cmd.size == 2) {
                if (!Data.whitelist.containsKey(event.senderID)) {
                    MiraiBot.getBot(event.botID).getFriend(event.senderID).sendMessage(
                        Config.WhiteListAddMessage.GetMessage()
                            .replace("%sender%" , event.senderNick)
                            .replace("%name%" , cmd[1])
                    )
                    Data.whitelist[event.senderID] = cmd[1]
                    Main.Instance.logger.info("已经为您添加白名单:${cmd[1]}")
                } else {
                    MiraiBot.getBot(event.botID).getFriend(event.senderID).sendMessage(
                        Config.WhiteListAddMessage.GetMessage()
                            .replace("%sender%" , event.senderNick)
                            .replace("%name%" , cmd[1])
                            .replace("%old_name%",Data.whitelist[event.senderID]?:" ")
                    )
                    Data.whitelist[event.senderID] = cmd[1]
                }
            }
            if (message == "#资料") {
                MiraiBot.getBot(event.botID).getFriend(event.senderID).sendMessage(Data.getPlayerData(event.senderID))
            }
        }
    }
}