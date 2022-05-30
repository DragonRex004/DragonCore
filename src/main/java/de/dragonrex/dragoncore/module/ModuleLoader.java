package de.dragonrex.dragoncore.module;

import com.google.gson.Gson;
import de.dragonrex.dragoncore.logger.Logger;
import de.dragonrex.dragoncore.logger.LoggerType;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleLoader {

    private final List<DragonModule> listServiceModule = new ArrayList<>();
    private final List<ModuleConfig> listModuleConfig = new ArrayList<>();
    private final Gson gson = new Gson();

    public ModuleLoader() throws Exception {
        loadAllModules();
    }

    private void loadAllModules() throws Exception {

        File file = new File("modules/");
        file.mkdirs();

        for (File listFile : file.listFiles()) {
            if (!listFile.getName().contains(".jar"))
                continue;
            String result = new BufferedReader(new InputStreamReader(getInputStreamFromRecource(listFile, "module.json")))
                    .lines().collect(Collectors.joining("\n"));
            ModuleConfig moduleConfig = this.gson.fromJson(result, ModuleConfig.class);
            DragonModule serviceModule = new ExtensionLoader<DragonModule>().loadClass(listFile, moduleConfig.getMainPath(), DragonModule.class);
            serviceModule.onEnable();

            this.listModuleConfig.add(moduleConfig);
            this.listServiceModule.add(serviceModule);
            Logger.log(LoggerType.INFO, "Loading module " + moduleConfig.getName() + " by " + moduleConfig.getAutore());
        }
    }

    public void disableAllModules() {
        for (DragonModule serviceModule : this.listServiceModule) {
            serviceModule.onDisable();
            Logger.log(LoggerType.INFO, "Disable module ");
        }
    }

    public void loadModuleByName(String name) {
        for (DragonModule serviceModule : this.listServiceModule) {
            for (ModuleConfig moduleConfig : this.listModuleConfig) {
                if(moduleConfig.getName().equals(name)) {
                    serviceModule.onEnable();
                    Logger.log(LoggerType.INFO, "Loading module " + moduleConfig.getName());
                }
            }
        }
    }

    public void disableModuleByName(String name) {
        for (DragonModule serviceModule : this.listServiceModule) {
            for (ModuleConfig moduleConfig : this.listModuleConfig) {
                if(moduleConfig.getName().equals(name)) {
                    serviceModule.onDisable();
                    Logger.log(LoggerType.INFO, "Disable module " + moduleConfig.getName());
                }
            }
        }
    }

    private  InputStream getInputStreamFromRecource(File file, String pathToRecource) throws MalformedURLException {
        return new URLClassLoader(new URL[]{file.toURI().toURL()}).getResourceAsStream(pathToRecource);
    }

    public List<ModuleConfig> getListModuleConfig() {
        return listModuleConfig;
    }

    public List<DragonModule> getListServiceModule() {
        return listServiceModule;
    }
}
