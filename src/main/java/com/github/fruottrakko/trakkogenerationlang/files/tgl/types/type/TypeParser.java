package com.github.fruottrakko.trakkogenerationlang.files.tgl.types.type;

public class TypeParser {

    private final static int SPLIT_LIMIT = 0;

    public static Type getType(String descriptor) {
        if(descriptor.isBlank()) {
            throw new IllegalArgumentException("A type declaration can't be empty!");
        }

        if(!descriptor.contains("$")) {
            return new Type(descriptor);
        }

        if(!descriptor.contains(",")) {
            return new Type(descriptor.substring(0, descriptor.indexOf("$")), descriptor.substring(descriptor.indexOf("$") + 1, descriptor.length()));
        }

        String[] arguments = descriptor.substring(descriptor.indexOf("$") + 1, descriptor.length()).split(",", SPLIT_LIMIT);
        return new Type(descriptor.substring(0, descriptor.indexOf("$")), arguments);
    }

}