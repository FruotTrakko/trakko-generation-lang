package com.github.fruottrakko.trakkogenerationlang.files.tgl.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.method.Method;
import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.method.MethodParser;

public class Methods {

    private List<Method> methods;

    public Methods(Map<String, String[]> methods) {
        this.methods = new ArrayList<Method>();

        for (Map.Entry<String, String[]> entry : methods.entrySet()) {
            this.methods.add(MethodParser.getMethod(entry.getKey(), entry.getValue()));
        }
    }

    public List<Method> getMethods() {
        return this.methods;
    }

}