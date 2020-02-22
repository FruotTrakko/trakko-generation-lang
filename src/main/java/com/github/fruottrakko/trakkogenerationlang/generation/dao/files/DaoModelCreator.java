package com.github.fruottrakko.trakkogenerationlang.generation.dao.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.Fields;
import com.github.fruottrakko.trakkogenerationlang.files.tglmap.TypeAtlases;
import com.github.fruottrakko.trakkogenerationlang.generation.dao.modules.DaoModelWriterModule;
import com.github.fruottrakko.trakkogenerationlang.modules.Modules;

public class DaoModelCreator {

    // Create file
    // Copy header
    // Invoke lang module generator with file refernece, fields, and type atlas as arguments

    private final static String DOMAIN_FILE_FORMAT = "%s.tgl-temp";

    public static void createModelFile(Path directoryPath, String domainName, String[] headerContent, Fields modelFields, TypeAtlases typeAtlases, String languageName) throws IOException {
        File modelFile = directoryPath.resolve(String.format(DOMAIN_FILE_FORMAT, domainName)).toFile();
        modelFile.createNewFile();

        try (FileWriter fileWriter = new FileWriter(modelFile)) {
            Files.write(modelFile.toPath(), Arrays.asList(headerContent), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new IOException(ex);
        }

        DaoModelWriterModule modelWriterModule = Modules.getModule(DaoModelWriterModule.class, languageName);
        modelWriterModule.writeDaoModelFileContent(modelFile, modelFields, typeAtlases.getLangTypeAtlas(languageName));
    }

}