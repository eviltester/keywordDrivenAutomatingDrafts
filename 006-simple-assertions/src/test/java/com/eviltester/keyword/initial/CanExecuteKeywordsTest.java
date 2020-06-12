package com.eviltester.keyword.initial;


import com.eviltester.keyword.execution.KeywordScriptExecutor;
import com.eviltester.keyword.script.KeywordScript;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CanExecuteKeywordsTest {

    @Test
    public void canExecuteKeywords(){

        List<String> keywordScriptList = new ArrayList();

        keywordScriptList.add("open \t chrome");
        keywordScriptList.add("get \t https://testpages.herokuapp.com/");
        keywordScriptList.add("sleep \t 2000");
        keywordScriptList.add("click \t Table Test Page"); // text
        keywordScriptList.add("sleep \t 2000");
        keywordScriptList.add("click \t div.page-navigation > a"); // css
        keywordScriptList.add("sleep \t 2000");
        keywordScriptList.add("click \t basicpagetest"); // id
        keywordScriptList.add("sleep \t 2000");
        keywordScriptList.add("click \t Index"); // id
        keywordScriptList.add("sleep \t 2000");
        keywordScriptList.add("click \t Alerts (JavaScript)");
        keywordScriptList.add("click \t alertexamples"); // id
        keywordScriptList.add("ok alert");
        keywordScriptList.add("click \t Show confirm box"); // handle finding an input by text ie. input[value='Show alert box']
        keywordScriptList.add("cancel alert");
        keywordScriptList.add("click \t Show prompt box");
        keywordScriptList.add("input alert \t Hello, this is my input.");
        keywordScriptList.add("can see text \t Hello, this is my input.");
        keywordScriptList.add("close");

        KeywordScript script = new KeywordScript().
                                        parse(keywordScriptList);

        KeywordScriptExecutor executor = new KeywordScriptExecutor().
                                                execute(script);

    }

}
