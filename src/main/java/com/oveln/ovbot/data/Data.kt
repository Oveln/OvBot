package com.oveln.ovbot.data

import com.oveln.ovbot.Main
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object Data {
    private val path = "plugins/${Main.Instance.description.name}/whitelist.yml"
    lateinit var whitelist: MutableMap<Long , String>

    fun getPlayerData(ID:Long):String {
        return "QQ:${ID}\n" +
                "白名单ID:${whitelist[ID]?:"无"}"
    }
    fun load() {
        val file = File(path)
        if (!file.exists()) file.createNewFile()
        val datafile = YamlConfiguration()
        datafile.load(file)
        whitelist = HashMap()
        datafile.getKeys(false).forEach() {
            whitelist[it.toLong()] = datafile.getString(it) ?: "None"
        }
    }
    fun save() {
        val file = File(path)
        if (!file.exists()) file.createNewFile()
        val datafile = YamlConfiguration()
        whitelist.keys.forEach() {
            datafile.set(it.toString() , whitelist[it])
        }
        datafile.save(file)
    }
}