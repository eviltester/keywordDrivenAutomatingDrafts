package com.eviltester.keyword.script;

import java.util.ArrayList;
import java.util.List;

public class KeywordScript {

    List<KeywordCommand> commands = new ArrayList<>();

    public KeywordScript parse(final List<String> keywordScriptList) {
        commands = new ArrayList<>();

        for(String scriptLine : keywordScriptList){
            KeywordCommand command = new KeywordCommand().parseTabDelimited(scriptLine);
            commands.add(command);
        }

        return this;
    }

    public int getLineCount() {
        return commands.size();
    }

    public KeywordCommand getLine(final int lineIndex) {
        return commands.get(lineIndex);
    }

    public KeywordScript addCommand(final KeywordCommand keywordCommand) {
        commands.add(keywordCommand);
        return this;
    }
}
