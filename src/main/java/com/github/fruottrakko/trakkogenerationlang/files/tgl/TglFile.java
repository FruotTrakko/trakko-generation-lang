package com.github.fruottrakko.trakkogenerationlang.files.tgl;

import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.Internal;
import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.type.Type;

public class TglFile {
    
    private Type exposedType;
    private Internal internalObject;

    protected TglFile(Type exposedType, Internal internalObject) {
        this.exposedType = exposedType;
        this.internalObject = internalObject;
    }

    public Type getExposedType() {
        return this.exposedType;
    }

    public Internal getInternalObject() {
        return this.internalObject;
    }

}