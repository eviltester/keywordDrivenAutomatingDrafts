package com.eviltester.keyword.script;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class KeywordScriptTest {

    @Test
    public void canCreateAScriptFromATabStringList(){

        List<String> keywordScriptList = new ArrayList();
        keywordScriptList.add("get \t https://testpages.herokuapp.com/");

        KeywordScript script = new KeywordScript().parse(keywordScriptList);

        Assertions.assertEquals(1,script.getLineCount());
        Assertions.assertEquals("GET",
                                script.getLine(0).getCommand());
        Assertions.assertEquals("https://testpages.herokuapp.com/",
                                script.getLine(0).getArgument(0));

    }
}
