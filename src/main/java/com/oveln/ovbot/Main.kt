package com.oveln.ovbot

import com.oveln.ovbot.data.Config
import com.oveln.ovbot.data.Data
import com.oveln.ovbot.listener.FriendMessageReceive
import com.oveln.ovbot.listener.GroupMessageRecevice
import com.oveln.ovbot.listener.PlayerLogin
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {
    companion object {
        lateinit var Instance : Main
    }
    override fun onEnable() {
        Instance = this

        saveDefaultConfig()
        Config.load()
        Data.load()

        Bukkit.getPluginManager().run {
            registerEvents(GroupMessageRecevice() ,this@Main)
            registerEvents(FriendMessageReceive() ,this@Main)
            registerEvents(PlayerLogin() , this@Main)
        }

        logger.info("&6${description.name}&f ${description.version}&2 启动成功   &c作者${description.authors}".colorful())
    }
    override fun onDisable() {
        Data.save()

        logger.info("&6${description.name}&f ${description.version}&2 关闭成功   &c作者${description.authors}".colorful())
    }
    fun String.colorful():String {
        return this.replace("&" , "§")
    }
}