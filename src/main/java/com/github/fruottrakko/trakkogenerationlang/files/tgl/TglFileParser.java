package com.github.fruottrakko.trakkogenerationlang.files.tgl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.Internal;
import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.type.TypeParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TglFileParser {

    private static final boolean SPLITERATOR_PARALLEL = false;

    public static TglFile getTglFile(String exposedType, Map<String, String> fields, Map<String, String[]> methods, boolean generateDao) {
        return new TglFile(TypeParser.getType(exposedType), new Internal(fields, methods), generateDao);
    }

    public static TglFile getTglFile(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        JsonObject internalObject = jsonObject.get(TglKeywords.INTERNAL.getName()).getAsJsonObject();

        Map<String, String> fields = new HashMap<String, String>();
        for (Map.Entry<String, JsonElement> entry : internalObject.get(TglKeywords.FIELDS.getName()).getAsJsonObject()
                .entrySet()) {
            fields.put(entry.getKey(), entry.getValue().getAsString());
        }

        Map<String, String[]> methods = new HashMap<String, String[]>();
        try {
            for (Map.Entry<String, JsonElement> entry : internalObject.get(TglKeywords.METHODS.getName()).getAsJsonObject()
                    .entrySet()) {
                methods.put(entry.getKey(),
                        StreamSupport.stream(entry.getValue().getAsJsonArray().spliterator(), SPLITERATOR_PARALLEL)
                                .map(jsonElement -> jsonElement.getAsString()).toArray(String[]::new));
            } 
        } catch (NullPointerException ex) {
            //nothing to do here. methods will be an empty map.
        }

        JsonElement generateDaoElement = jsonObject.get(TglKeywords.GENERATE_DAO.getName());
        boolean generateDao;
        if (generateDaoElement == null) {
            generateDao = false;
        } else {
            generateDao = generateDaoElement.getAsBoolean();
        }

        return getTglFile(jsonObject.get(TglKeywords.EXPOSED.getName()).getAsString(), fields, methods, generateDao);
    }

    public static TglFile getTglFile(Path tglFile) {
        String jsonString;
        try {
            jsonString = Files.lines(tglFile).collect(Collectors.joining());
        } catch (IOException ex) {
            throw new IllegalArgumentException(new StringBuilder("Illegal input file: ").append(ex).toString());
        }

        return getTglFile(jsonString);
    }

}