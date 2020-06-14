package com.eviltester.keyword.mainlike;

import com.eviltester.keyword.data.DataDrivenConfig;
import com.eviltester.keyword.data.DataFileReader;
import com.eviltester.keyword.execution.KeywordScriptExecutor;
import com.eviltester.keyword.logging.KeywordScriptLog;
import com.eviltester.keyword.logging.ScriptExecutionReport;
import com.eviltester.keyword.script.KeywordScript;
import com.eviltester.keyword.scriptfile.ScriptFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class MakeItEasyToCreateAppTest {

    /* some of the tests are 'like' a main method.
       make these easier to model as a main method by creating fewer entry points into
       the library.

       Ideally as short and linear possible

     */

    @Test
    public void easyCreateDataDrivenExecution() throws IOException {

        KeywordScriptLog log = new KeywordScriptLog();

        log.debug("Loading data files");

        DataFileReader filereader = new DataFileReader();

        File dataFileToRead = new File( getClass().getClassLoader().
                getResource("mainline/data.tab").
                getFile());

        // todo trap this and report
        DataDrivenConfig data = filereader.readTabDelimited(dataFileToRead);

        File scriptFileToRead = new File( getClass().getClassLoader().
                getResource("mainline/script.tab").
                getFile());

        // todo trap this and report
        KeywordScript script = new ScriptFileReader().readTabDelimited(scriptFileToRead);

        KeywordScriptExecutor executor =
                new KeywordScriptExecutor(log).
                        stopOnFailure().
                        execute(script, data);

        System.out.println(new ScriptExecutionReport(log.getReportDetails()).toString());

        // fail this test if the script execution fails
        if(!log.getReportDetails().executionStatus()){
            throw new RuntimeException("Script Execution Failed");
        }

    }
}
