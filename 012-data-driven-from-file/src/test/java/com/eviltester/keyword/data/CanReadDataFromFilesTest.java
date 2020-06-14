package com.eviltester.keyword.data;

import com.eviltester.keyword.script.KeywordScript;
import com.eviltester.keyword.scriptfile.ScriptFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class CanReadDataFromFilesTest {

    @Test
    public void canReadDataFromFile() throws IOException {

        DataFileReader filereader = new DataFileReader();

        File fileToRead = new File( getClass().getClassLoader().
                getResource("datadriven/tab/simple-data.tab").
                getFile());

        DataDrivenConfig data = filereader.readTabDelimited(fileToRead);

        Assertions.assertEquals(2, data.countOfDataEntryLines());
        Assertions.assertEquals("value1", data.getDataEntries(0).get("data-item-1"));
        Assertions.assertEquals("value2", data.getDataEntries(0).get("data-item-2"));
        Assertions.assertEquals("value3", data.getDataEntries(1).get("data-item-1"));
        Assertions.assertEquals("value4", data.getDataEntries(1).get("data-item-2"));
    }

    // todo handle file does not exist, return empty script and report error

    @Test
    public void canReadDataFromExcelFile() throws IOException {

        DataFileReader filereader = new DataFileReader();

        File fileToRead = new File( getClass().getClassLoader().
                getResource("datadriven/excel/simple-data.xlsx").
                getFile());

        DataDrivenConfig data = filereader.readExcel(fileToRead);

        Assertions.assertEquals(2, data.countOfDataEntryLines());
        Assertions.assertEquals("value1", data.getDataEntries(0).get("data-item-1"));
        Assertions.assertEquals("value2", data.getDataEntries(0).get("data-item-2"));
        Assertions.assertEquals("value3", data.getDataEntries(1).get("data-item-1"));
        Assertions.assertEquals("value4", data.getDataEntries(1).get("data-item-2"));
    }
}
