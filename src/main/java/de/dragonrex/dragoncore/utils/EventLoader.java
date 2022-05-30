package de.dragonrex.dragoncore.utils;

import de.dragonrex.dragoncore.DragonCore;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class EventLoader {

    public static void  registerEvent(Listener listener, DragonCore core) {
        Bukkit.getPluginManager().registerEvents(listener, core);
    }
}
