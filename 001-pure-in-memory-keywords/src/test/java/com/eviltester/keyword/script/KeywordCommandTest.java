package com.eviltester.keyword.script;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KeywordCommandTest {

    @Test
    public void canParseTabDelimitedKeywordString(){

        final KeywordCommand command = new KeywordCommand(
                                    "get \t https://eviltester.com");

        Assertions.assertEquals("GET", command.getCommand());
        Assertions.assertEquals("https://eviltester.com",
                                    command.getArgument(0));
        Assertions.assertEquals(1, command.countOfArguments());

    }

    // TODO: have command templates for syntax checking e.g. OPEN url:URL
    // TODO: report on errors

}
