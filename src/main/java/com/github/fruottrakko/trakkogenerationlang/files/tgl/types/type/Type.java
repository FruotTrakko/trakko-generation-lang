package com.github.fruottrakko.trakkogenerationlang.files.tgl.types.type;

import java.util.List;
import java.util.Optional;

public class Type {

    private String name;
    private Optional<List<String>> parameter;

    protected Type(String name) {
        this.name = name;
    }

    protected Type(String name, String... parameter) {
        this(name);

        this.parameter = Optional.of(List.of(parameter));
    }

    public String getName() {
        return this.name;
    }

    public Optional<List<String>> getParameter() {
        return this.parameter;
    }

}