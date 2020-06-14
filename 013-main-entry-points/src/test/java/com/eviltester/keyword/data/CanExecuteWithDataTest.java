package com.eviltester.keyword.data;


import com.eviltester.keyword.execution.KeywordScriptExecutor;
import com.eviltester.keyword.logging.KeywordScriptLog;
import com.eviltester.keyword.logging.ScriptExecutionReport;
import com.eviltester.keyword.script.KeywordScript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CanExecuteWithDataTest {

    @Test
    public void canExecuteWithData(){

        // I will initially make this a tab delimited list of name value pairs
        DataDrivenConfig dataDriven = new DataDrivenConfig();
        dataDriven.addTabDelimitedLine("alert-text-to-enter \t This is Input 1");
        dataDriven.addTabDelimitedLine("alert-text-to-enter \t !@£$%^&*()"); // punctuation
        dataDriven.addTabDelimitedLine("alert-text-to-enter \t Z"); // single char


        KeywordScriptLog log = new KeywordScriptLog();

        log.debug("Configuring Script In Memory");

        List<String> keywordScriptList = new ArrayList();

        keywordScriptList.add("open \t chrome");
        keywordScriptList.add("get \t https://testpages.herokuapp.com/");
        keywordScriptList.add("sleep \t 1000");

        keywordScriptList.add("click \t Alerts (JavaScript)");

        keywordScriptList.add("check \t false \t can see text \t {{alert-text-to-enter}}");

        keywordScriptList.add("click \t Show prompt box");

        //keywordScriptList.add("input alert \t Hello, this is my input.");
        keywordScriptList.add("alert \t input \t {{alert-text-to-enter}}");
        keywordScriptList.add("sleep \t 1000");

        keywordScriptList.add("check \t true \t can see text \t {{alert-text-to-enter}}");
        keywordScriptList.add("close");

        KeywordScript script = new KeywordScript().
                                        parse(keywordScriptList);

        for(int dataLine=0; dataLine<dataDriven.countOfDataEntryLines(); dataLine++){

            final KeywordScriptLog scriptLog = new KeywordScriptLog();

            try {
                KeywordScriptExecutor executor =
                        new KeywordScriptExecutor(scriptLog).
                                stopOnFailure().
                                execute(script, dataDriven.getDataEntries(dataLine));
            }catch(Exception e){
                // prevent the app failing because we want to generate a report
                log.debug("Script Execution Failed with Exception");
            }

            log.addScriptLog(scriptLog);
        }

        // TODO: if embedded scripts then the main report should have Total Scripts Execution Status, and Total Scripts Execution Time
        // TODO: output the data lines used as well as the script
        System.out.println(new ScriptExecutionReport(log.getReportDetails()).toString());

        // fail this test if the script execution fails
        Assertions.assertTrue(log.getReportDetails().executionStatus());




    }

}
