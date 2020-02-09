package com.github.fruottrakko.trakkogenerationlang.files.tgl.types.method;

import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.method.actions.Action;

public class Method {

    private String name;
    private Action[] actions;

    protected Method(String name, Action[] actions) {
        this.name = name;
        this.actions = actions;
    }

    public String getName() {
        return this.name;
    }

    public Action[] getActions() {
        return this.actions;
    }

}