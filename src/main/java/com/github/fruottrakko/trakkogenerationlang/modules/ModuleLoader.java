package com.github.fruottrakko.trakkogenerationlang.modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ModuleLoader {

    //TODO REFACTORING
    public static List<BaseModule> loadModulesFromFile(File jarFile) throws FileNotFoundException, IOException {
        List<BaseModule> result = new ArrayList<BaseModule>();

        ClassLoader classLoader = new URLClassLoader(new URL[] { jarFile.toURI().toURL() });

        JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarFile));

        JarEntry jarEntry;
        while ((jarEntry = jarInputStream.getNextJarEntry()) != null) {
            if (!jarEntry.getName().toLowerCase().endsWith(".class")) {
                continue;
            }

            try {
                Class<?> cls = classLoader
                        .loadClass(jarEntry.getName().substring(0, jarEntry.getName().length() - 6).replace('/', '.'));

                for (Class<?> interfaces : cls.getInterfaces()) {
                    if (interfaces.equals(BaseModule.class)) {
                        result.add(((Class<BaseModule>) cls).getDeclaredConstructor().newInstance());
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        jarInputStream.close();
        return result;
    }

}