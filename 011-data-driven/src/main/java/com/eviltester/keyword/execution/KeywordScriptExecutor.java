package com.eviltester.keyword.execution;

import com.eviltester.keyword.execution.commands.*;
import com.eviltester.keyword.execution.commands.alerts.*;
import com.eviltester.keyword.execution.commands.assertions.AssertHandlingCommand;
import com.eviltester.keyword.execution.commands.assertions.AssertTextExists;
import com.eviltester.keyword.logging.KeywordScriptLog;
import com.eviltester.keyword.script.KeywordCommand;
import com.eviltester.keyword.script.KeywordScript;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeywordScriptExecutor {

    private KeywordScriptLog log=new KeywordScriptLog();
    Map<String, KeywordExecutor> commandHandlers = new HashMap<>();
    ExecutionStateStore state;

    private boolean stopOnFailure;

    public KeywordScriptExecutor(final KeywordScriptLog log) {
        this.log = log;
        configureDefaults();
    }

    public KeywordScriptExecutor(){
        configureDefaults();
    }

    private void configureDefaults(){
        this.stopOnFailure=false;

        log.debug("Configuring Command Handlers");

        // register command handlers
        commandHandlers.put("OPEN", new OpenCommand());
        commandHandlers.put("CLOSE", new CloseCommand());
        commandHandlers.put("GET", new GetCommand());
        commandHandlers.put("SLEEP", new SleepCommand());
        commandHandlers.put("CLICK", new ClickCommand());

        // alert handling
        commandHandlers.put("ALERT", new AlertHandlingCommand());
        commandHandlers.put("OK ALERT", new CloseAlertCommand());
        commandHandlers.put("INPUT ALERT", new InputAlertCommand());
        commandHandlers.put("CANCEL ALERT", new DismissAlertCommand());

        // assertions
        commandHandlers.put("CHECK", new AssertHandlingCommand());
        commandHandlers.put("CAN SEE TEXT", new AssertTextExists());
    }


    public KeywordScriptExecutor execute(final KeywordScript script, final Map<String, String> dataEntries) {

        log.startScriptExecution(script);
        log.usingData(dataEntries);

        state = new ExecutionStateStore(log);

        WebDriverManager.chromedriver().setup();

        for(int lineIndex=0; lineIndex<script.getLineCount(); lineIndex++){

            KeywordCommand commandToExecute = script.getLine(lineIndex).withData(dataEntries);

            log.startExecuteScriptLine(lineIndex, commandToExecute);

            try {
                boolean passed = executeCommand(commandToExecute);
                if(passed){
                    log.commandPassed(lineIndex, commandToExecute);
                }else{
                    throw new RuntimeException("Test Step Failed");
                }
            }catch(Exception e){
                log.commandFailed(lineIndex, commandToExecute);
                log.exceptionRaised(e);
                state.close();
                throw e;
            }
        }

        log.endScriptExecution(script);

        return this;
    }



    public KeywordScriptExecutor execute(final KeywordScript script) {
        return execute(script, new HashMap<String,String>());
    }

    private boolean executeCommand(final KeywordCommand line) {

        final KeywordExecutor command = commandHandlers.get(line.getCommand());
        if(command==null){
            log.debug("SYNTAX ERROR: Unknown command - " + line);
            throw new RuntimeException("Unknown command " + line);
        }

        return command.execute(state, line);
    }

    public KeywordScriptExecutor stopOnFailure() {
        this.stopOnFailure=true;
        return this;
    }


}
