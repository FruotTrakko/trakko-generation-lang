package com.github.fruottrakko.trakkogenerationlang.files.tgl.types.type;

public class TypeParser {

    private final static String PARAMETER_SYMBOL = "$";
    private final static String PARAMETER_SEPARATOR = ",";
    private final static int SPLIT_LIMIT = 0;

    public static Type getType(String descriptor) {
        if(descriptor.isBlank()) {
            throw new IllegalArgumentException("A type declaration can't be empty!");
        }

        if(!descriptor.contains(PARAMETER_SYMBOL)) {
            return new Type(descriptor);
        }

        if(!descriptor.contains(PARAMETER_SEPARATOR)) {
            return new Type(descriptor.substring(0, descriptor.indexOf(PARAMETER_SYMBOL)), descriptor.substring(descriptor.indexOf(PARAMETER_SYMBOL) + 1, descriptor.length()));
        }

        String[] arguments = descriptor.substring(descriptor.indexOf(PARAMETER_SYMBOL) + 1, descriptor.length()).split(PARAMETER_SEPARATOR, SPLIT_LIMIT);
        return new Type(descriptor.substring(0, descriptor.indexOf(PARAMETER_SYMBOL)), arguments);
    }

}