package com.eviltester.keyword.logging;

import com.eviltester.keyword.script.KeywordCommand;
import com.eviltester.keyword.script.KeywordScript;

public class KeywordScriptLog {


    public void debug(final String message) {
        System.out.println(message);
    }

    public void startScriptExecution(final KeywordScript script) {
        debug("Starting Script Execution");
    }

    public void startExecuteScriptLine(final int lineIndex, final KeywordCommand line) {
        debug(String.format("Executing Script Line %d: %s", lineIndex, line.toString()));
    }

    public void commandPassed(final int lineIndex, final KeywordCommand line) {
        debug(String.format("Executing Script Line %d: PASSED - %s", lineIndex, line.toString()));
    }

    public void commandFailed(final int lineIndex, final KeywordCommand line) {
        debug(String.format("Executing Script Line %d: FAILED - %s", lineIndex, line.toString()));
    }

    public void exceptionRaised(final Exception e) {
        debug("Exception Raised: " + e.getMessage());
        e.printStackTrace();
    }

    public void endScriptExecution(final KeywordScript script) {
        debug("Starting Script Execution");
    }
}
