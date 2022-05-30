package de.dragonrex.dragoncore.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class CommandBase extends BukkitCommand {
    private String permission;

    public CommandBase(String name, String permission) {
        super(name);
        this.permission = permission;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("handle no player exception");
            return false;
        } else if (!sender.hasPermission(this.permission)) {
            sender.sendMessage("handle no permissions exception");
            return false;
        } else {
            this.execute((Player) sender, args);
            return false;
        }
    }

    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return this.tabCompleter(args);
    }

    public abstract CommandResponse execute(Player player, String[] args);

    public abstract List<String> tabCompleter(String[] args);
}
