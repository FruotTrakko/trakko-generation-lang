package com.github.fruottrakko.trakkogenerationlang.modules;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

public class Modules {

    private final static String MODULE_PATH = "./modules/";

    private static Map<String, LangModule> langModules;

    static {
        for (File moduleFile : Paths.get(MODULE_PATH).toFile().listFiles(new JarFileFilter())) {
            try {
                ModuleLoader.loadModuleFromFile(moduleFile).ifPresent(module -> {
                    langModules.put(module.getLanguageName(), module);
                });
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static Optional<LangModule> getModule(String languageName) {
        return Optional.ofNullable(langModules.get(languageName));
    }

}