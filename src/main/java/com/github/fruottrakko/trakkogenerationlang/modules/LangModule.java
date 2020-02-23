package com.github.fruottrakko.trakkogenerationlang.modules;

import com.github.fruottrakko.trakkogenerationlang.generation.dao.modules.DaoModelWriterModule;

public interface LangModule extends DaoModelWriterModule{
    
    public String getLanguageName();

}