package com.github.fruottrakko.trakkogenerationlang.modules;

import java.io.File;
import java.io.FileFilter;

public class JarFileFilter implements FileFilter {

    private final static String JAR_EXTENSION = ".jar";

    @Override
    public boolean accept(File pathname) {
        if(pathname.getName().endsWith(JAR_EXTENSION)) {
            return true;
        }
        return false;
    }
    
}