package com.eviltester.keyword.scriptfile;

import com.eviltester.keyword.script.KeywordScript;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


public class ScriptFileReader {
    public KeywordScript readTabDelimited(final File fileToRead) throws IOException {
        List<String> lines = Files.readAllLines(fileToRead.toPath());
        return new KeywordScript().parse(lines);
    }
}
