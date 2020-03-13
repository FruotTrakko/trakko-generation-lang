package com.github.fruottrakko.trakkogenerationlang.modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.annotation.Nullable;

public class ModuleLoader {

    private final static Class<LangModule> LANG_MODULE_CLASS = LangModule.class;
    private final static String CLASS_FILE_SUFFIX = ".class";
    private final static String NO_LANG_MODULE_JAR = "Jar file %s doesn't contains a valid subclass of LangModule!";

    public static Optional<LangModule> loadModuleFromFile(File jarFile) {
        try {
            Class<LangModule> langModuleClass = loadModuleClass(jarFile);
            if (langModuleClass == null) {
                return Optional.empty();
            }

            return Optional.of(createModuleInstance(langModuleClass));
        } catch (InvalidLangModuleException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }

    }

    @Nullable
    private static Class<LangModule> loadModuleClass(File jarFile) throws InvalidLangModuleException {
        try (URLClassLoader urlClassLoader = new URLClassLoader(getJarFileUrls(jarFile))) {
            try (JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarFile))) {
                JarEntry jarEntry;
                while ((jarEntry = jarInputStream.getNextJarEntry()) != null) {
                    if (!isClassFile(jarEntry)) {
                        continue;
                    }

                    Class<LangModule> clz = loadModuleClass(urlClassLoader, jarEntry);
                    if(clz != null) {
                        return clz;
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                throw new IOException(ex);
            }
        } catch (IOException ex) {
            throw new InvalidLangModuleException(ex);
        }
        throw new InvalidLangModuleException(String.format(NO_LANG_MODULE_JAR, jarFile.getName()));
    }

    @SuppressWarnings("unchecked")
    // the cast is checked before hand, so safe to cast
    private static Class<LangModule> loadModuleClass(URLClassLoader urlClassLoader, JarEntry jarEntry)
            throws ClassNotFoundException {
        Class <?> clz = urlClassLoader.loadClass(getJarEntryClassPath(jarEntry));

        if (containsModuleInterface(clz)) {
            return (Class<LangModule>) clz;
        }
        return null;
    }

    private static URL[] getJarFileUrls(File jarFile) throws MalformedURLException {
        return new URL[] { jarFile.toURI().toURL() };
    }

    private static boolean isClassFile(JarEntry jarEntry) {
        return jarEntry.getName().toLowerCase().endsWith(CLASS_FILE_SUFFIX);
    }

    //TODO Refactor seperators?
    private static String getJarEntryClassPath(JarEntry jarEntry) {
        String entryName = jarEntry.getName();
        return entryName.substring(0, entryName.length() - CLASS_FILE_SUFFIX.length()).replace('/', '.');
    }

    private static boolean containsModuleInterface(Class<?> clz) {
        for (Class<?> interfaceClz : clz.getInterfaces()) {
            if (interfaceClz.equals(LANG_MODULE_CLASS)) {
                return true;
            }
        }
        return false;
    }

    private static LangModule createModuleInstance(Class<LangModule> langModuleClass)
            throws InvalidLangModuleException {
        try {
            return langModuleClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException ex) {
            throw new InvalidLangModuleException(ex);
        }
    }
}