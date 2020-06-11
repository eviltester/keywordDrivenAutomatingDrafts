package com.eviltester.keyword.execution.commands;

import com.eviltester.keyword.execution.ExecutionStateStore;
import com.eviltester.keyword.execution.KeywordExecutor;
import com.eviltester.keyword.script.KeywordCommand;
import org.openqa.selenium.By;

public class AssertTextExists implements KeywordExecutor {
    @Override
    public boolean execute(final ExecutionStateStore state, final KeywordCommand command) {
        if(!state.getDriver().findElement(By.tagName("body")).getText().contains(command.getArgument(0))){
            throw new RuntimeException("FAILED: " + command.toString());
        };
        return true;
    }

    @Override
    public String getName() {
        return "ASSERT-TEXT-EXISTS";
    }
}
