package com.eviltester.keyword.logging;

import com.eviltester.keyword.script.KeywordCommand;
import com.eviltester.keyword.script.KeywordScript;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeywordScriptLog {

/*
    Logging is done as we work.

    Reporting is done after.

    Future changes might be to have different levels of logging.
    Different logging outputs.
    Could add in a logging framework or library and delegate to it.

    Reporting will require building up a 'model' of what happens then
    view the model as different formats.

    Combine start executing script line, command passed and command failed into one 'event'
 */

    KeywordScriptExecutionEventReport report = new KeywordScriptExecutionEventReport();

    public void debug(final String message) {
        System.out.println(message);
    }

    public void startScriptExecution(final KeywordScript script) {
        debug("Starting Script Execution");
        report.forScript(script).startNow();
    }

    public void startExecuteScriptLine(final int lineIndex, final KeywordCommand line) {
        debug(String.format("Executing Script Line %d: %s", lineIndex, line.toString()));
        report.lineExecutionStart(lineIndex, line);
    }

    public void commandPassed(final int lineIndex, final KeywordCommand line) {
        report.lineExecutionEnd(lineIndex, line, true);
        debug(String.format("Executing Script Line %d: PASSED - %s", lineIndex, line.toString()));
    }

    public void commandFailed(final int lineIndex, final KeywordCommand line) {
        report.lineExecutionEnd(lineIndex, line, false);
        debug(String.format("Executing Script Line %d: FAILED - %s", lineIndex, line.toString()));
    }

    public void exceptionRaised(final Exception e) {
        debug("Exception Raised: " + e.getMessage());
        e.printStackTrace();
    }

    public void endScriptExecution(final KeywordScript script) {
        report.endNow();
        debug("Ending Script Execution");
    }

    public KeywordScriptExecutionEventReport getReportDetails() {
        return report;
    }

    public void usingData(final Map<String, String> dataEntries) {
        if(dataEntries==null || dataEntries.size()==0){
            debug("No Data Entries");
        }else{

            debug("Using Data:");
            for(Map.Entry<String,String> entry : dataEntries.entrySet()){
                debug(String.format("{{%s}} : %s", entry.getKey(), entry.getValue()));
            }

            report.usingData(dataEntries);

        }
    }

    public void addScriptLog(final KeywordScriptLog scriptLog) {
        report.addEmbeddedScriptReport(scriptLog.getReportDetails());
        report.endNow();
        if(!scriptLog.getReportDetails().executionStatus()){
            report.setExecutionStatus(false);
        }
    }
}
