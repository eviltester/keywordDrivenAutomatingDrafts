package com.eviltester.keyword.execution.commands;

import com.eviltester.keyword.execution.ExecutionStateStore;
import com.eviltester.keyword.execution.KeywordExecutor;
import com.eviltester.keyword.script.KeywordCommand;

public class CloseCommand implements KeywordExecutor {
    @Override
    public boolean execute(final ExecutionStateStore state, final KeywordCommand command) {
        state.getDriver().close();
        state.setDriver(null);
        return true;
    }

    @Override
    public String getName() {
        return "CLOSE";
    }
}
