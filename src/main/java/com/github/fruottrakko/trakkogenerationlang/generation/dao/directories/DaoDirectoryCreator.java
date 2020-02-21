package com.github.fruottrakko.trakkogenerationlang.generation.dao.directories;

import java.nio.file.Path;
import java.util.stream.Stream;

public class DaoDirectoryCreator {
    
    private static final String DAO_PACKAGE_NAME = "dao";

    //Generate Dirs: filename.dao.<dbnames>

    public static void createDirectoryTree(Path destinationPath, String domainName, String[] databaseNames) {
        Path domainPackagePath = createAndReturnDirectoryByPath(destinationPath, domainName);

        Path daoPackagePath = createAndReturnDirectoryByPath(domainPackagePath, DAO_PACKAGE_NAME);

        Stream.of(databaseNames).parallel()
            .forEach((databaseName) -> {
                createAndReturnDirectoryByPath(daoPackagePath, databaseName);
            });
    }

    private static Path createAndReturnDirectoryByPath(Path path, String name) {
        Path newDirectory = path.resolve(name);
        newDirectory.toFile().mkdirs();

        return newDirectory;
    }

}