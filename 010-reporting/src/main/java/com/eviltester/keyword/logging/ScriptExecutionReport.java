package com.eviltester.keyword.logging;

public class ScriptExecutionReport {

    private final KeywordScriptExecutionEventReport reportDetails;

    public ScriptExecutionReport(final KeywordScriptExecutionEventReport reportDetails) {
        this.reportDetails = reportDetails;
    }

    @Override
    public String toString() {

        final StringBuilder reportOutput = new StringBuilder();

        reportOutput.append(String.format("%n---%n"));
        reportOutput.append(String.format("SCRIPT EXECUTION STATUS: %b %n", reportDetails.executionStatus()));
        reportOutput.append(String.format("---%n"));

        reportOutput.append(String.format("%nScript:%n%n"));
        for(int line=0; line<reportDetails.getScript().getLineCount(); line++){
            reportOutput.append(String.format("- %d: %s%n", line+1,
                                reportDetails.getScript().getLine(line).toString()));
        }

        reportOutput.append(String.format("%nExecution Report:%n%n"));

        reportOutput.append(String.format("| Passed | Command | Args |%n"));

        for( KeywordScriptExecutionEventReport.CommandExecutionLog logLine : reportDetails.getExecutionLog()){

            String args = "";
            String prefix = "";

            for(int arg=0; arg<logLine.getCommand().countOfArguments(); arg++){
                args = args + prefix + logLine.getCommand().getArgument(arg);
                prefix = ", ";
            }

            reportOutput.append(String.format("| %b | %s | %s |%n",
                                                logLine.passedStatus(),
                                                logLine.getCommand().getCommand(),
                                                args));
        }

        reportOutput.append(String.format("%n%nScript Execution Duration: %d seconds%n",
                (reportDetails.endTimeMillis() - reportDetails.startTimeMillis())/1000));

        return reportOutput.toString();
    }
}
