package com.eviltester.keyword.execution.commands;

import com.eviltester.keyword.execution.ExecutionStateStore;
import com.eviltester.keyword.execution.KeywordExecutor;
import com.eviltester.keyword.script.KeywordCommand;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenCommand implements KeywordExecutor {

    @Override
    public boolean execute(final ExecutionStateStore state, final KeywordCommand command) {

        String browserName = command.getArgument(0).toUpperCase();
        if(browserName.equals("CHROME")){
            state.setDriver(new ChromeDriver());
        }else{
            throw new RuntimeException("Unknown Driver: " + browserName);
        }
        return true;
    }

    @Override
    public String getName() {
        return "OPEN";
    }
}
