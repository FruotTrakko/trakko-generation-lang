package com.github.fruottrakko.trakkogenerationlang.generation.directories;

import java.nio.file.Path;

import com.github.fruottrakko.trakkogenerationlang.scraper.DirectoryScraper;
import com.github.fruottrakko.trakkogenerationlang.scraper.DirectoryTree;

public class DirectoryCleaner {
    
    public static void cleanupDirectoryTree(Path cleanupPath) {
        cleanupDirectoryTree(DirectoryScraper.getDirectoryTree(cleanupPath), cleanupPath);
    }

    public static void cleanupDirectoryTree(DirectoryTree cleanupTree, Path cleanupPath) {
        cleanupTree.cleanupDirectoriesRecursive(cleanupPath);
    }

}