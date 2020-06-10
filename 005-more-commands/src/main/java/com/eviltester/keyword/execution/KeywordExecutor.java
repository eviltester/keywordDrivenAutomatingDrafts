package com.eviltester.keyword.execution;

import com.eviltester.keyword.script.KeywordCommand;

public interface KeywordExecutor {

    public boolean execute(final ExecutionStateStore state, final KeywordCommand command);

    String getName();
}
