package com.eviltester.keyword.execution;

import com.eviltester.keyword.script.KeywordCommand;
import com.eviltester.keyword.script.KeywordScript;
import io.github.bonigarcia.wdm.WebDriverManager;

public class KeywordScriptExecutor {
    public KeywordScriptExecutor execute(final KeywordScript script) {

        WebDriverManager.chromedriver().setup();

        for(int lineIndex=0; lineIndex<script.getLineCount(); lineIndex++){
            executeCommand(script.getLine(lineIndex));
        }

        return this;
    }

    private void executeCommand(final KeywordCommand line) {

        switch (line.getCommand()){
            case "OPEN":


        }

        return;
    }
}
