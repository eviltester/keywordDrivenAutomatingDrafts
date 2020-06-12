package com.eviltester.keyword.execution.commands.alerts;

import com.eviltester.keyword.execution.ExecutionStateStore;
import com.eviltester.keyword.execution.KeywordExecutor;
import com.eviltester.keyword.script.KeywordCommand;

import java.util.ArrayList;
import java.util.List;

public class AlertHandlingCommand implements KeywordExecutor {

    @Override
    public boolean execute(final ExecutionStateStore state, final KeywordCommand command) {

        // choose the action based on the first argument
        String action = command.getArgument(0).toUpperCase();
        List<String> actionArgs = new ArrayList<>();
        for(int actionArgIndex=1; actionArgIndex<command.countOfArguments(); actionArgIndex++){
            actionArgs.add(command.getArgument(actionArgIndex));
        }
        final KeywordCommand actionCommand = new KeywordCommand(action, actionArgs);

        // TODO: should probably rename these Alert commands to be clearer
        //  e.g. OkAlert (instead of Close), CancelAlert (instead of Dismiss)
        // DONE: add to an alerts package under the commands package
        // TODO: if I only wanted one of these then I could refactor inline
        //       and remove the old commands
        switch (action){
            case "OK":
                return new CloseAlertCommand().execute(state, actionCommand);
            case "CANCEL":
                return new DismissAlertCommand().execute(state, actionCommand);
            case "INPUT":
                return new InputAlertCommand().execute(state, actionCommand);
            default:
                new RuntimeException("Unrecognised Command:" + command.toString());
        }

        return false;
    }

    @Override
    public String getName() {
        return "ALERT";
    }
}
