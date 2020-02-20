package com.github.fruottrakko.trakkogenerationlang.generation.directories;

import java.nio.file.Path;

import com.github.fruottrakko.trakkogenerationlang.scraper.DirectoryScraper;
import com.github.fruottrakko.trakkogenerationlang.scraper.DirectoryTree;

public class DirectoryCloner {

    public static void cloneDirectoryTree(Path sourcePath, Path destinationPath) {
        cloneDirectoryTree(DirectoryScraper.getDirectoryTree(sourcePath), destinationPath);
    }

    public static void cloneDirectoryTree(DirectoryTree sourceTree, Path destinationPath) {
        sourceTree.createDirectoriesRecursive(destinationPath);
    }

}