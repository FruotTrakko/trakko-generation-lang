package com.github.fruottrakko.trakkogenerationlang.files.tgl;

import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.Internal;
import com.github.fruottrakko.trakkogenerationlang.files.tgl.types.type.Type;

public class TglFile {
    
    private Type exposedType;
    private Internal internalObject;
    private boolean generateDao;

    protected TglFile(Type exposedType, Internal internalObject, boolean generateDao) {
        this.exposedType = exposedType;
        this.internalObject = internalObject;
        this.generateDao = generateDao;
    }

    public Type getExposedType() {
        return this.exposedType;
    }

    public Internal getInternalObject() {
        return this.internalObject;
    }

    public boolean getGenerateDao() {
        return this.generateDao;
    }

}