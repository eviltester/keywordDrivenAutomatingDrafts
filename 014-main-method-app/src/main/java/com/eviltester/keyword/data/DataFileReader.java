package com.eviltester.keyword.data;

import com.eviltester.keyword.script.KeywordCommand;
import com.eviltester.keyword.script.KeywordScript;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class DataFileReader {

    public DataDrivenConfig readTabDelimited(final File fileToRead) throws IOException {
        List<String> lines = Files.readAllLines(fileToRead.toPath());
        return new DataDrivenConfig().parse(lines);
    }

    public DataDrivenConfig readExcel(final File fileToRead) throws IOException {
        FileInputStream fileStream = new FileInputStream(fileToRead);

        // https://poi.apache.org/components/spreadsheet/quick-guide.html#ReadWriteWorkbook

        Workbook workbook = WorkbookFactory.create(fileStream);

        //Get first/desired sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);

        DataDrivenConfig data = new DataDrivenConfig();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            String name=null;
            String value=null;
            Map<String, String> dataItems = new HashMap<>();

            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();

                String cellValue=null;

                if(cell.getCellType() == CellType.NUMERIC){

                    final double doubleVal = cell.getNumericCellValue();

                    // convert to integer if not really a float
                    if (doubleVal == Math.floor(doubleVal)){
                        cellValue = String.valueOf(
                                Double.valueOf(
                                        cell.getNumericCellValue())
                                        .intValue());
                    }else{
                        cellValue = String.valueOf(
                                cell.getNumericCellValue());
                    }
                }
                if(cell.getCellType() == CellType.STRING){
                    cellValue = cell.getStringCellValue();
                }

                if(name==null){
                    name = cellValue;
                }else{
                    value = cellValue;
                    dataItems.put(name, value);
                    name=null;
                    value = null;

                }
            }

            data.addMapAsLine(dataItems);
        }

        fileStream.close();

        return data;
    }
}

