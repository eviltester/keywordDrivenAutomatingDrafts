package com.eviltester.keyword.scriptfile;

import com.eviltester.keyword.script.KeywordCommand;
import com.eviltester.keyword.script.KeywordScript;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ScriptFileReader {

    public KeywordScript readTabDelimited(final File fileToRead) throws IOException {
        List<String> lines = Files.readAllLines(fileToRead.toPath());
        return new KeywordScript().parse(lines);
    }

    // TODO handle file read errors

    public KeywordScript readExcel(final File fileToRead) throws IOException {
        FileInputStream fileStream = new FileInputStream(fileToRead);

        // https://poi.apache.org/components/spreadsheet/quick-guide.html#ReadWriteWorkbook

        Workbook workbook = WorkbookFactory.create(fileStream);

        //Get first/desired sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);

        KeywordScript script = new KeywordScript();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            String command=null;
            List<String> args = new ArrayList<>();

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

                if(command==null){
                    command = cellValue;
                }else{
                    if(cellValue!=null) {
                        args.add(cellValue);
                    }
                }
            }

            final KeywordCommand keywordCommand = new KeywordCommand(command, args);
            script.addCommand(keywordCommand);
        }

        fileStream.close();

        return script;
    }
}
