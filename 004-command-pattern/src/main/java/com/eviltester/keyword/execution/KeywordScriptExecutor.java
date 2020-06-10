package com.eviltester.keyword.execution;

import com.eviltester.keyword.execution.commands.CloseCommand;
import com.eviltester.keyword.execution.commands.GetCommand;
import com.eviltester.keyword.execution.commands.OpenCommand;
import com.eviltester.keyword.execution.commands.SleepCommand;
import com.eviltester.keyword.script.KeywordCommand;
import com.eviltester.keyword.script.KeywordScript;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class KeywordScriptExecutor {

    Map<String, KeywordExecutor> commandHandlers = new HashMap<>();
    ExecutionStateStore state;

    public KeywordScriptExecutor(){

        // register command handlers
        commandHandlers.put("OPEN", new OpenCommand());
        commandHandlers.put("CLOSE", new CloseCommand());
        commandHandlers.put("GET", new GetCommand());
        commandHandlers.put("SLEEP", new SleepCommand());

    }
    public KeywordScriptExecutor execute(final KeywordScript script) {

        state = new ExecutionStateStore();

        WebDriverManager.chromedriver().setup();

        for(int lineIndex=0; lineIndex<script.getLineCount(); lineIndex++){
            executeCommand(script.getLine(lineIndex));
        }

        return this;
    }

    private void executeCommand(final KeywordCommand line) {

        final KeywordExecutor command = commandHandlers.get(line.getCommand());
        if(command==null){
            throw new RuntimeException("Unknown command " + line);
        }

        command.execute(state, line);
    }
}
