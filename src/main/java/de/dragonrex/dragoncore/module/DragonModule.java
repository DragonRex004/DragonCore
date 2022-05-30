package de.dragonrex.dragoncore.module;

import de.dragonrex.dragoncore.DragonCore;
import de.dragonrex.dragoncore.utils.DragonCommand;
import de.dragonrex.dragoncore.utils.EventLoader;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;

import java.lang.reflect.Field;

public abstract class DragonModule {

    public void onDisable() {}

    public void onEnable() {}

    public void registerEvent(Listener listener) {
        EventLoader.registerEvent(listener, DragonCore.getInstance());
    }

    public void registerCommand(DragonCommand command) {
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register("TestModule", command);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
