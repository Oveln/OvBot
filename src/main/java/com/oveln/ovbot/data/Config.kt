package com.oveln.ovbot.data

import com.oveln.ovbot.Main

object Config {
    lateinit var QQGroup: String
    lateinit var KickMessage: String
    lateinit var HelpMessage: String
    fun load() {
        QQGroup = Main.Instance.config.getString("QQGroup")?:"183995431"
        KickMessage = Main.Instance.config.getString("KickMessage")?:"你不在白名单里！"
        HelpMessage = ""
        val HelpMessageList = Main.Instance.config.getStringList("HelpMessage")
        HelpMessageList.forEachIndexed() {index,it->
            HelpMessage += it
            if (index != HelpMessageList.size-1) HelpMessage += '\n'
        }
    }
}