package com.eviltester.keyword.execution.commands;

import com.eviltester.keyword.execution.ExecutionStateStore;
import com.eviltester.keyword.execution.KeywordExecutor;
import com.eviltester.keyword.script.KeywordCommand;

public class GetCommand implements KeywordExecutor {
    @Override
    public boolean execute(final ExecutionStateStore state, final KeywordCommand command) {
        state.getDriver().get(command.getArgument(0));
        return true;
    }

    @Override
    public String getName() {
        return "GET";
    }
}
