package com.github.fruottrakko.trakkogenerationlang.files.tgl.types;

import java.util.HashMap;
import java.util.Map;

import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.type.Type;
import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.type.TypeParser;

public class Fields {

    private Map<String, Type> fields;

    public Fields(Map<String, String> entries) {
        this.fields = new HashMap<String, Type>();

        for (Map.Entry<String, String> entry : entries.entrySet()) {
            this.fields.put(entry.getKey(), TypeParser.getType(entry.getValue()));
        }
    }

    public Map<String, Type> getFields() {
        return this.fields;
    }

}