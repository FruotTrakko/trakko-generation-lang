package com.github.fruottrakko.trakkogenerationlang.files.tgl;

public enum TglKeywords {

    EXPOSED("$exposed"),
    INTERNAL("$internal"),
    FIELDS("$fields"),
    METHODS("$methods"),
    EXPOSE("$expose");

    private String name;

    private TglKeywords(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}