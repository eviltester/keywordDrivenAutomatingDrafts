package com.eviltester.keyword.initial;


import com.eviltester.keyword.execution.KeywordScriptExecutor;
import com.eviltester.keyword.script.KeywordScript;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CanExecuteKeywords {

    @Test
    public void canExecuteKeywords(){

        List<String> keywordScriptList = new ArrayList();

        keywordScriptList.add("open \t chrome");
        keywordScriptList.add("get \t https://testpages.herokuapp.com/");
        keywordScriptList.add("sleep \t 2000");
        keywordScriptList.add("close");

        KeywordScript script = new KeywordScript().
                                        parse(keywordScriptList);

        KeywordScriptExecutor executor = new KeywordScriptExecutor().
                                                execute(script);

    }

}
