package de.dragonrex.dragoncore;

import de.dragonrex.dragoncore.module.ModuleLoader;
import de.dragonrex.dragoncore.utils.EventLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class DragonCore extends JavaPlugin {
    private static DragonCore instance;
    private ModuleLoader loader;

    @Override
    public void onEnable() {
        instance = this;
        try {
            this.loader = new ModuleLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        loader.disableAllModules();
    }

    public static DragonCore getInstance() {
        return instance;
    }

    public ModuleLoader getLoader() {
        return this.loader;
    }
}
