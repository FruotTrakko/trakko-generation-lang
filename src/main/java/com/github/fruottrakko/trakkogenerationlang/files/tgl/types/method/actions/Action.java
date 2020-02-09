package com.github.fruottrakko.trakkogenerationlang.files.tgl.types.method.actions;

import java.util.NoSuchElementException;

public class Action {

    private ActionType type;
    private String value;

    public Action(ActionType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Action(String type, String value) throws NoSuchElementException {
        this(ActionType.getByName(type).get(), value);
    }

    public Action(String descriptor) throws ArrayIndexOutOfBoundsException{
        this(descriptor.split(":")[0], descriptor.split(":")[1]);
    }

    public ActionType getActionType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }
}