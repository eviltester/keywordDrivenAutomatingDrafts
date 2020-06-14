package com.eviltester.keyword.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDrivenConfig {


    List<Map<String, String>> nameValueEntries;

    public DataDrivenConfig(){
        this.nameValueEntries = new ArrayList<>();
    }

    public void addTabDelimitedLine(final String tabDelimited) {

        Map<String, String> nameValues = new HashMap<>();

        String[] nameValuePairs = tabDelimited.split("\t");

        for(int index=0; index<nameValuePairs.length; index+=2){

            String name = nameValuePairs[index].trim();
            String value = nameValuePairs[index+1].trim();

            nameValues.put(name, value);
        }

        nameValueEntries.add(nameValues);
    }

    public int countOfDataEntryLines() {
        return nameValueEntries.size();
    }

    public Map<String, String> getDataEntries(final int dataLineIndex) {
        return nameValueEntries.get(dataLineIndex);
    }
}
