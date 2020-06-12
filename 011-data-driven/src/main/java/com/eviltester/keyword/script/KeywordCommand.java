package com.eviltester.keyword.script;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeywordCommand {

    String command;
    List<String> arguments;

    public KeywordCommand(){
        arguments=new ArrayList<>();
    }

    public KeywordCommand(final String command, final List<String> args) {
        this.command = command.toUpperCase();
        arguments = new ArrayList<>();
        arguments.addAll(args);
    }

    public KeywordCommand parseTabDelimited(final String scriptLine){

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
        return this;
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

    public KeywordCommand withData(final Map<String, String> dataEntries) {
        String commandVerb = dataExpansion(command, dataEntries);
        List<String> args = new ArrayList<>();

        for(String arg : arguments){
            args.add(dataExpansion(arg, dataEntries));
        }

        return new KeywordCommand(commandVerb, args);
    }

    private String dataExpansion(final String stringToExpand, final Map<String, String> dataEntries) {
        String toReturn = stringToExpand;
        for(String key : dataEntries.keySet()){
            toReturn = toReturn.replace("{{" + key + "}}", dataEntries.get(key));
        }
        return toReturn;
    }
}
