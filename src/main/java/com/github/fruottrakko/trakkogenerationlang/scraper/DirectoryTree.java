package com.github.fruottrakko.trakkogenerationlang.scraper;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DirectoryTree {

    private String directoryName;
    private DirectoryTree[] childDirectoryTrees;

    public DirectoryTree(Path path) {
        this.directoryName = path.toFile().getName();

        this.childDirectoryTrees = Stream.of(path.toFile().listFiles())
            .filter(file -> file.isDirectory())
            .map(file -> file.toPath())
            .map(DirectoryTree::new)
            .toArray(DirectoryTree[]::new);
    }

    public String getDirectoryName() {
        return this.directoryName;
    }

    public DirectoryTree[] getChildDirectoryTrees() {
        return this.childDirectoryTrees;
    }

    public List<DirectoryTree> getChildDirectoryTreesAsList() {
        return Arrays.asList(this.childDirectoryTrees);
    }

    public Path getDirectoryPath(Path parentPath) {
        return parentPath.resolve(this.directoryName);
    }

    public void createDirectory(Path destinationPath) {
        getDirectoryPath(destinationPath).toFile().mkdirs();
    }

    public void createDirectoriesRecursive(Path destinationPath) {
        createDirectory(destinationPath);
        getChildDirectoryTreesAsList().parallelStream()
            .forEach((tree) -> {
                tree.createDirectoriesRecursive(getDirectoryPath(destinationPath));
            });
    }

    public void cleanupDirectory(Path cleanupPath) {
        if (this.childDirectoryTrees.length != 0) {
            return;
        }

        if (getDirectoryPath(cleanupPath).toFile().listFiles().length != 0) {
            return;
        }

        getDirectoryPath(cleanupPath).toFile().delete();
    }

	public void cleanupDirectoriesRecursive(Path cleanupPath) {
        cleanupDirectory(cleanupPath);
        getChildDirectoryTreesAsList().parallelStream()
            .forEach((tree) -> {
                tree.cleanupDirectoriesRecursive(getDirectoryPath(cleanupPath));
            });
	}

}