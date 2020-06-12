package com.eviltester.keyword.initial;


import com.eviltester.keyword.execution.KeywordScriptExecutor;
import com.eviltester.keyword.script.KeywordScript;
import com.eviltester.keyword.scriptfile.ScriptFileReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CanReadKeywordScriptsFromFileTest {

    @Test
    public void canExecuteKeywords() throws IOException {

        File fileToRead = new File( getClass().getClassLoader().
                getResource("testscripts/tab/simple-tab-delimited.tab").
                getFile());

        KeywordScript script = new ScriptFileReader().readTabDelimited(fileToRead);

        KeywordScriptExecutor executor = new KeywordScriptExecutor().
                                                execute(script);
    }

    @Test
    public void canExecuteExcelKeywords() throws IOException {

        File fileToRead = new File( getClass().getClassLoader().
                getResource("testscripts/excel/simple-script.xlsx").
                getFile());

        KeywordScript script = new ScriptFileReader().readExcel(fileToRead);

        KeywordScriptExecutor executor = new KeywordScriptExecutor().
                execute(script);
    }

}
