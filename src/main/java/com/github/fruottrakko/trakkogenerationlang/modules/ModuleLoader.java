package com.github.fruottrakko.trakkogenerationlang.modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ModuleLoader {

    //TODO REFACTORING
    public static Optional<LangModule> loadModuleFromFile(File jarFile) throws FileNotFoundException, IOException {
        LangModule result = null;

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
                    if (interfaces.equals(LangModule.class)) {
                        result = (LangModule) cls.getDeclaredConstructor().newInstance();
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        jarInputStream.close();
        return Optional.ofNullable(result);
    }

}