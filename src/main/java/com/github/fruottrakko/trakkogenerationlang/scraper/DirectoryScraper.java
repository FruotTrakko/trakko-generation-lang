package com.github.fruottrakko.trakkogenerationlang.scraper;

import java.nio.file.Path;

import javax.annotation.Nullable;

public class DirectoryScraper {

    @Nullable
    public static DirectoryTree getDirectoryTree(Path path) {
        if (!path.toFile().isDirectory()) {
           System.err.printf("Working directory can't be a file. Given: %s", path.toAbsolutePath());
           return null;
        }

        return new DirectoryTree(path);
    }

}