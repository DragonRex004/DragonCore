package de.dragonrex.dragoncore.utils;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public abstract class DragonCommand extends CommandBase {
    private List<String> subCommands = new ArrayList<>();

    public DragonCommand(String name, String permission) {
        super(name, permission);
    }

    public abstract CommandResponse execute(Player player, String[] args);

    public abstract List<String> tabCompleter(String[] args);

    public void addSubCommandToList(String name) {
        this.subCommands.add(name);
    }

    public List<String> getSubCommands() {
        return this.subCommands;
    }
}
