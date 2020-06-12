package com.eviltester.keyword.execution;

import com.eviltester.keyword.execution.commands.*;
import com.eviltester.keyword.execution.commands.alerts.*;
import com.eviltester.keyword.execution.commands.assertions.AssertHandlingCommand;
import com.eviltester.keyword.execution.commands.assertions.AssertTextExists;
import com.eviltester.keyword.logging.KeywordScriptLog;
import com.eviltester.keyword.script.KeywordCommand;
import com.eviltester.keyword.script.KeywordScript;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;

public class KeywordScriptExecutor {

    private KeywordScriptLog log=new KeywordScriptLog();
    Map<String, KeywordExecutor> commandHandlers = new HashMap<>();
    ExecutionStateStore state;

    private boolean stopOnFailure;

    public KeywordScriptExecutor(final KeywordScriptLog log) {
        this.log = log;
    }

    public KeywordScriptExecutor(){

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



    public KeywordScriptExecutor execute(final KeywordScript script) {

        log.startScriptExecution(script);

        state = new ExecutionStateStore(log);

        WebDriverManager.chromedriver().setup();

        for(int lineIndex=0; lineIndex<script.getLineCount(); lineIndex++){

            log.startExecuteScriptLine(lineIndex, script.getLine(lineIndex));

            try {
                boolean passed = executeCommand(script.getLine(lineIndex));
                if(passed){
                    log.commandPassed(lineIndex, script.getLine(lineIndex));
                }else{
                    throw new RuntimeException("Test Step Failed");
                }
            }catch(Exception e){
                log.commandFailed(lineIndex, script.getLine(lineIndex));
                log.exceptionRaised(e);
                state.close();
                throw e;
            }
        }

        log.endScriptExecution(script);

        return this;
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
