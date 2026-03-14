package uk.ac.ucl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    private void addColumnNames(DataFrame dataFrame, CSVRecord csvRow)
    {
        for (int i = 0; i < csvRow.size(); i++ ){
            String colName = csvRow.get(i);
            dataFrame.addColumn(colName);
        }
    }

    private void addValues(DataFrame dataFrame, CSVRecord csvRow,
                          List<String > columnNames)
    {
        for (int i = 0; i < csvRow.size(); i++ ){
            dataFrame.addValue(columnNames.get(i), csvRow.get(i) );
        }
    }

    public DataFrame loadData(String filename){
        DataFrame dataFrame = new DataFrame();
        CSVFormat format = CSVFormat.DEFAULT;
        List<String> columnNames = new ArrayList<>();

        try(Reader reader = new FileReader(filename);
            CSVParser csvParser = new CSVParser(reader,format))
        {
            for (CSVRecord csvRow: csvParser){
                if (csvRow.getRecordNumber() == 1){
                    // first row in file is column name, so add it first
                    addColumnNames(dataFrame,csvRow);
                    columnNames = dataFrame.getColumnNames();
                }
                else{
                    addValues(dataFrame,csvRow,columnNames);
                }
            }

        }catch (IOException e){
            throw new RuntimeException("File not found: " + filename, e);
        }
        return dataFrame;
    }
}
