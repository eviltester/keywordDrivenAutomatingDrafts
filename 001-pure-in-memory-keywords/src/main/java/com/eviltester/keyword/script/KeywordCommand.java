package com.eviltester.keyword.script;

import java.util.ArrayList;
import java.util.List;

public class KeywordCommand {

    String command;
    List<String> arguments;

    public KeywordCommand(final String scriptLine) {

        arguments=new ArrayList<>();

        // parse this using |\t
        final String[] words = scriptLine.split("\t");
        for(String word : words){
            String wordToStore = word.trim();
            if(wordToStore.length()>0){
                if(command==null){
                    command = wordToStore.toUpperCase();
                }else{
                    arguments.add(wordToStore);
                }
            }
        }

    }

    public String getCommand() {
        return command;
    }

    public String getArgument(final int index) {
        return arguments.get(index);
    }

    public int countOfArguments() {
        return arguments.size();
    }

    @Override
    public String toString() {
        String asString = command;
        for(String arg : arguments){
            asString = asString + " " + arg;
        }
        return asString;
    }
}
