package com.oveln.ovbot.listener

import com.oveln.ovbot.data.Config
import com.oveln.ovbot.data.Data
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent

class PlayerLogin: Listener {
    @EventHandler
    fun onListened(event: AsyncPlayerPreLoginEvent) {
        if (!Data.whitelist.containsValue(event.name)) {
            event.disallow(
                AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST,
                Config.KickMessages.GetMessage()
            )
        }
    }
}