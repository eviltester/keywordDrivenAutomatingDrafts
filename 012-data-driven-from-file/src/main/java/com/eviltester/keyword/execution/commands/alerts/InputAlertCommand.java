package com.eviltester.keyword.execution.commands.alerts;

import com.eviltester.keyword.execution.ExecutionStateStore;
import com.eviltester.keyword.execution.KeywordExecutor;
import com.eviltester.keyword.script.KeywordCommand;
import org.openqa.selenium.WebDriver;

public class InputAlertCommand implements KeywordExecutor {
    @Override
    public boolean execute(final ExecutionStateStore state, final KeywordCommand command) {
        WebDriver driver = state.getDriver();
        try {
            driver.switchTo().alert().sendKeys(command.getArgument(0));
            driver.switchTo().alert().accept();
        }catch (Exception e){
            state.getLog().debug("Input Alert Failed");
            state.getLog().exceptionRaised(e);
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "INPUT-ALERT";
    }
}
