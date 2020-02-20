package com.github.fruottrakko.trakkogenerationlang.files.tgl.types;

import java.util.Map;
import java.util.Optional;

public class Internal {

    private Fields fields;
    private Optional<Methods> methods;

    public Internal(Map<String, String> fields, Map<String, String[]> methods) {
        this.fields = new Fields(fields);
        this.methods = Optional.of(new Methods(methods));
    }

    public Fields getFields() {
        return this.fields;
    }

    public Methods getMethods() {
        return this.methods.orElse(null);
    }

}