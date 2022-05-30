package de.dragonrex.dragoncore.module;

public class ModuleConfig {

    private final String name, autore, mainPath;

    public ModuleConfig(String name, String autore, String mainPath) {
        this.name = name;
        this.mainPath = mainPath;
        this.autore = autore;
    }

    public String getName() {
        return name;
    }

    public String getAutore() {
        return autore;
    }

    public String getMainPath() {
        return mainPath;
    }
}