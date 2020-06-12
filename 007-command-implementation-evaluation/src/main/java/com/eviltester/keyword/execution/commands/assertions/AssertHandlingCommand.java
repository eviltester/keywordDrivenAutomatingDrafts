package com.eviltester.keyword.execution.commands.assertions;

import com.eviltester.keyword.execution.ExecutionStateStore;
import com.eviltester.keyword.execution.KeywordExecutor;
import com.eviltester.keyword.execution.commands.alerts.CloseAlertCommand;
import com.eviltester.keyword.execution.commands.alerts.DismissAlertCommand;
import com.eviltester.keyword.execution.commands.alerts.InputAlertCommand;
import com.eviltester.keyword.script.KeywordCommand;

import java.util.ArrayList;
import java.util.List;

// built based on the AlertHandlingCommand - same basic pattern
public class AssertHandlingCommand implements KeywordExecutor {

    @Override
    public boolean execute(final ExecutionStateStore state, final KeywordCommand command) {

        // assertion is true or false
        String assertTrueFalse = command.getArgument(0).toUpperCase();
        boolean compareAssertionResult = true;
        if(assertTrueFalse.equals("TRUE") || assertTrueFalse.equals("FALSE")){
            // syntax correct
            if(assertTrueFalse.equals("FALSE")){
                compareAssertionResult=false;
            }
        }else{
            new RuntimeException("Unrecognised Command:" + command.toString());
        }

        String assertionCommand = command.getArgument(1).toUpperCase();

        List<String> assertionArgs = new ArrayList<>();
        for(int actionArgIndex=2; actionArgIndex<command.countOfArguments(); actionArgIndex++){
            assertionArgs.add(command.getArgument(actionArgIndex));
        }
        final KeywordCommand assertion = new KeywordCommand(assertionCommand, assertionArgs);

        boolean assertionResult=false;

        // TODO: pass in the handler map and we can assert on any action
        switch (assertionCommand){
            case "CAN SEE TEXT":
                // need to wrap this in try catch since assertion throws runtime exception on false
                // but we can now assert on falsehood
                try {
                    assertionResult = new AssertTextExists().execute(state, assertion);
                }catch (Exception e){
                    assertionResult = false;
                }
            default:
                new RuntimeException("Unrecognised Assertion:" + command.toString());
        }

        if(compareAssertionResult == assertionResult){
            return true;
        }

        //return false;
        throw new RuntimeException("Assertion Failed: " + assertion.toString());
    }

    @Override
    public String getName() {
        return "ASSERT";
    }
}
