package com.eviltester.keyword;

import com.eviltester.keyword.data.DataDrivenConfig;
import com.eviltester.keyword.data.DataFileReader;
import com.eviltester.keyword.execution.KeywordScriptExecutor;
import com.eviltester.keyword.logging.KeywordScriptLog;
import com.eviltester.keyword.logging.ScriptExecutionReport;
import com.eviltester.keyword.script.KeywordScript;
import com.eviltester.keyword.scriptfile.ScriptFileReader;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;

public class KeywordDrivenApp {

    public static void main( String[] args ) {
        // create the parser
        CommandLineParser parser = new DefaultParser();
        Options options = configureCommandLineOptions();

        CommandLine line=null;

        try {
            // parse the command line arguments
            line = parser.parse( options, args );
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Cannot Execute.  Reason: " + exp.getMessage() );
            System.exit(-1);
        }

        // now the 'test' that we created

        KeywordScriptLog log = new KeywordScriptLog();

        log.debug("Loading data files");

        DataDrivenConfig data=null;

        if(line.hasOption("data")){
            DataFileReader filereader = new DataFileReader();
            File dataFileToRead = new File(line.getOptionValue("data"));
            if(!dataFileToRead.exists()){
                throw new RuntimeException("Could not find data file :" + dataFileToRead.getAbsolutePath());
            }

            try {
                if(dataFileToRead.getName().endsWith(".tab")) {
                    data = filereader.readTabDelimited(dataFileToRead);
                }else{
                    data = filereader.readExcel(dataFileToRead);
                }
            } catch (IOException e) {
                log.debug("Error loading file " + dataFileToRead.getAbsolutePath());
                log.exceptionRaised(e);
                e.printStackTrace();
            }
        }


        File scriptFileToRead = new File(line.getOptionValue("script"));
        if(!scriptFileToRead.exists()){
            throw new RuntimeException("Could not find script file :" + scriptFileToRead.getAbsolutePath());
        }

        KeywordScript script=null;

        ScriptFileReader scriptReader = new ScriptFileReader();
        try {
            if(scriptFileToRead.getName().endsWith(".tab")) {
                script = scriptReader.readTabDelimited(scriptFileToRead);
            }else{
                script = scriptReader.readExcel(scriptFileToRead);
            }
        } catch (IOException e) {
            log.debug("Error loading file " + scriptFileToRead.getAbsolutePath());
            log.exceptionRaised(e);
            e.printStackTrace();
        }


        if(data!=null) {
            KeywordScriptExecutor executor =
                    new KeywordScriptExecutor(log).
                            stopOnFailure().
                            execute(script, data);
        }else{
            KeywordScriptExecutor executor =
                    new KeywordScriptExecutor(log).
                            stopOnFailure().
                            execute(script);
        }

        System.out.println(new ScriptExecutionReport(log.getReportDetails()).toString());

        // fail this test if the script execution fails
        if(!log.getReportDetails().executionStatus()){
            throw new RuntimeException("Script Execution Failed");
        }
    }

    private static Options configureCommandLineOptions() {

        Options options = new Options();

        Option datafile   = Option.builder( "data" )
                .hasArg()
                .desc(  "use given file for data driven execution" )
                .build();

        options.addOption(datafile);

        Option scriptfile   = Option.builder("script")
                .hasArg()
                .desc(  "use given file for script" )
                .required(true)
                .build();

        options.addOption(scriptfile);

        return options;
    }
}
