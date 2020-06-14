package com.eviltester.keyword.logging;

import com.eviltester.keyword.script.KeywordCommand;
import com.eviltester.keyword.script.KeywordScript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeywordScriptExecutionEventReport {
    private KeywordScript script;
    private long startTime;
    private long endTime;
    private CommandExecutionLog currentCommand;
    List<CommandExecutionLog> commandsExecutedLog;
    private boolean executionPassed;
    private Map<String, String> data;
    List<KeywordScriptExecutionEventReport> embeddedScriptExecutionReports;

    public KeywordScriptExecutionEventReport(){
        embeddedScriptExecutionReports = new ArrayList<>();
        this.commandsExecutedLog = new ArrayList<>();
        this.executionPassed=true;
        data = new HashMap<>();
        startTime=System.currentTimeMillis();
        endTime=startTime;
    }

    public KeywordScriptExecutionEventReport forScript(final KeywordScript script) {
        this.script = script;
        return this;
    }

    public KeywordScriptExecutionEventReport startNow() {
        this.startTime = System.currentTimeMillis();
        return this;
    }

    public KeywordScriptExecutionEventReport endNow() {
        this.endTime = System.currentTimeMillis();
        return this;
    }

    public KeywordScriptExecutionEventReport lineExecutionStart(final int lineIndex, final KeywordCommand line) {
        this.currentCommand = new CommandExecutionLog(lineIndex, line);
        this.commandsExecutedLog.add(currentCommand);
        return this;
    }

    public KeywordScriptExecutionEventReport lineExecutionEnd(final int lineIndex, final KeywordCommand line, final boolean passed) {
        if(!currentCommand.matches(line)){
            throw new RuntimeException(
                    String.format("Tried to report command execution finish without starting - %d - %s",
                            lineIndex, line.toString()));
        }

        this.currentCommand.markAsExecuted(passed);

        if(!passed){
            this.executionPassed=false;
        }

        return this;
    }

    public boolean executionStatus() {
        return executionPassed;
    }

    public KeywordScript getScript() {
        return this.script;
    }

    public List<CommandExecutionLog> getExecutionLog() {
        return this.commandsExecutedLog;
    }

    public long endTimeMillis() {
        return endTime;
    }

    public long startTimeMillis() {
        return startTime;
    }

    public void usingData(final Map<String, String> dataEntries) {
        this.data = dataEntries;
    }

    public List<KeywordScriptExecutionEventReport> getEmbeddedScriptReports() {
        return embeddedScriptExecutionReports;
    }

    public void addEmbeddedScriptReport(final KeywordScriptExecutionEventReport reportDetails) {
        embeddedScriptExecutionReports.add(reportDetails);
    }

    public boolean hasScript() {
        return script!=null;
    }

    public void setExecutionStatus(final boolean passed) {
        this.executionPassed=passed;
    }

    public class CommandExecutionLog{

        private final int lineNumber;
        private final KeywordCommand command;
        private final long startTime;
        private boolean passed;
        private long endTime;

        public CommandExecutionLog(int lineNumber, KeywordCommand command){
            this.lineNumber = lineNumber;
            this.command = command;
            this.startTime = System.currentTimeMillis();
            this.passed = false;
        }

        public boolean matches(final KeywordCommand line) {
            return line==command;
        }

        public void markAsExecuted(final boolean passed) {
            this.passed=passed;
            this.endTime = System.currentTimeMillis();
        }

        public boolean passedStatus() {
            return passed;
        }

        public KeywordCommand getCommand() {
            return command;
        }
    }
}
