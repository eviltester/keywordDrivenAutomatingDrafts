package com.eviltester.keyword.execution.commands.alerts;

import com.eviltester.keyword.execution.ExecutionStateStore;
import com.eviltester.keyword.execution.KeywordExecutor;
import com.eviltester.keyword.script.KeywordCommand;
import org.openqa.selenium.WebDriver;

public class CloseAlertCommand implements KeywordExecutor {
    @Override
    public boolean execute(final ExecutionStateStore state, final KeywordCommand command) {
        WebDriver driver = state.getDriver();
        try {
            driver.switchTo().alert().accept();
        }catch (Exception e){
            state.getLog().debug("Closing Alert Failed");
            state.getLog().exceptionRaised(e);
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "CLOSE-ALERT";
    }
}
