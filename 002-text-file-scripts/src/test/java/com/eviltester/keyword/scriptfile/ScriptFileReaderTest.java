package com.eviltester.keyword.scriptfile;

import com.eviltester.keyword.script.KeywordScript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ScriptFileReaderTest {

    @Test
    public void canReadScriptFromFile() throws IOException {

        ScriptFileReader filereader = new ScriptFileReader();

        // intial, can I get the file
//        File fileToRead = new File(System.getProperty("user.dir"),
//                                    "src/test/resources/testscripts/tab/simple-tab-delimited.tab");

        // converted to resource to make more robust for testing
        File fileToRead = new File( getClass().getClassLoader().
                                    getResource("testscripts/tab/simple-tab-delimited.tab").
                                    getFile());

        KeywordScript script = filereader.readTabDelimited(fileToRead);

        Assertions.assertEquals(4,script.getLineCount());
        Assertions.assertEquals("OPEN", script.getLine(0).getCommand());
    }

    // todo handle file does not exist, return empty script and report error

}
