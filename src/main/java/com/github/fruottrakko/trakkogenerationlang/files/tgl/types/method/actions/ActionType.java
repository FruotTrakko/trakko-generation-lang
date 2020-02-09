package com.github.fruottrakko.trakkogenerationlang.files.tgl.types.method.actions;

import java.util.Optional;
import java.util.stream.Stream;

public enum ActionType {

    COMMENT("$comment"),
    CODE("$code"),
    LOGIC("$logic");

    private String name;

    private ActionType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Optional<ActionType> getByName(String name) {
        return Stream.of(ActionType.values()).parallel().filter(type -> type.getName().equalsIgnoreCase(name)).findAny();
    }

}