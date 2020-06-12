package com.eviltester.keyword.initial;


import com.eviltester.keyword.execution.KeywordScriptExecutor;
import com.eviltester.keyword.logging.KeywordScriptLog;
import com.eviltester.keyword.script.KeywordScript;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CanExecuteKeywordsTest {

    @Test
    public void canExecuteKeywords(){

        // At the moment, I 'log' things to system.out
        // I don't really know what I want to output or where
        // I might need a test report in the future etc.
        // I will create a contextual logging approach i.e. a custom class

        KeywordScriptLog log = new KeywordScriptLog();

        log.debug("Configuring Script In Memory");

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

        // Alerts
        keywordScriptList.add("click \t Alerts (JavaScript)");
        keywordScriptList.add("click \t alertexamples"); // id

        // instead of: ok alert, cancel alert, input alert
        // should it be alert \t cancel i.e. "action" as first param
        // we can have a single command 'alert' for all 'alert' handling
        //keywordScriptList.add("ok alert");
        keywordScriptList.add("alert \t ok"); // alert is the 'command', ok is the argument

        keywordScriptList.add("click \t Show confirm box"); // handle finding an input by text ie. input[value='Show alert box']
        //keywordScriptList.add("cancel alert");
        keywordScriptList.add("alert \t cancel"); // alert is the 'command' cancel is the param


        // Assertions - DONE: created an assertions package
        // instead of "can see text" add check as the main command
        // check \t true \t can see text \t Hello, this is my input.
        // check \t false \t can see text \t Hello, this is my input.


        keywordScriptList.add("check \t false \t can see text \t Hello, this is my input.");

        keywordScriptList.add("click \t Show prompt box");
        //keywordScriptList.add("input alert \t Hello, this is my input.");
        keywordScriptList.add("alert \t input \t Hello, this is my input.");

        keywordScriptList.add("check \t true \t can see text \t Hello, this is my input.");
        keywordScriptList.add("close");

        KeywordScript script = new KeywordScript().
                                        parse(keywordScriptList);

        KeywordScriptExecutor executor = new KeywordScriptExecutor().
                                                stopOnFailure().
                                                execute(script);

    }

}
