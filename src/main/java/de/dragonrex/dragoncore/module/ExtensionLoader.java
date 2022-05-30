package de.dragonrex.dragoncore.module;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ExtensionLoader<C> {

    public C loadClass(File jar, String classpath, Class<C> parentClass) throws Exception {
        try {
            File[] plugins = new File("modules/").listFiles();
            List<URL> urls = new ArrayList<>();
            for (File plugin : plugins) {
                if (plugin.getName().endsWith(".jar")) {
                    urls.add(plugin.toURI().toURL());
                }
            }
            urls.add(jar.toURI().toURL());

            URLClassLoader urlClassLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]), getClass().getClassLoader());
            Class<?> clazz = Class.forName(classpath, true, urlClassLoader);
            Class<? extends C> newClass = clazz.asSubclass(parentClass);

            Constructor<? extends C> constructor = newClass.getConstructor();
            return constructor.newInstance();

        } catch (Exception exception) {
            throw exception;
        }
    }
}