package com.github.fruottrakko.trakkogenerationlang.files.tgl.types.method;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.method.actions.Action;

public class MethodParser {

    public static Method getMethod(String name, String[] actions) {
        try {
            return new Method(name, Stream.of(actions).map(Action::new).toArray(Action[]::new));
        } catch (ArrayIndexOutOfBoundsException | NoSuchElementException ex) {
            throw new IllegalArgumentException(new StringBuilder("Couldn't parse method: ").append(ex.getMessage()).toString());
        }
    }

}