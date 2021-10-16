package com.oveln.ovbot.data

import com.oveln.ovbot.Main
import com.oveln.ovbot.message.Message

object Config {
    lateinit var QQGroup: String
    lateinit var KickMessages: Message
    lateinit var OnlinePlayer: Message
    lateinit var WhiteListAddMessage: Message
    lateinit var WhiteListChangeMessage: Message
    lateinit var HelpMessage: String
    fun load() {
        val config = Main.Instance.config
        QQGroup = config.getString("QQGroup")?:""
        KickMessages = Message(config.getStringList("KickMessages"))
        OnlinePlayer = Message(config.getStringList("OnlinePlayerKey"),config.getStringList("OnlinePlayerMessages"))
        WhiteListAddMessage = Message(config.getStringList("WhiteListAddMessages"))
        WhiteListChangeMessage = Message(config.getStringList("WhiteListChangeMessages"))
        HelpMessage = ""
        val HelpMessageList = config.getStringList("HelpMessage")
        HelpMessageList.forEachIndexed() {index,it->
            HelpMessage += it
            if (index != HelpMessageList.size-1) HelpMessage += '\n'
        }
    }
}