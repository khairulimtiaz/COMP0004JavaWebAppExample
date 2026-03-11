package uk.ac.ucl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class DataLoader {

    private void addColumnNames(DataFrame dataFrame, CSVRecord row)
    {
        for (int i = 0; i < row.size(); i++ ){
            String colName = row.get(i);
            dataFrame.addColumn(colName);
        }
    }

    private void addValues(DataFrame dataFrame, CSVRecord row,
                          ArrayList<String > columnNames)
    {
        for (int i = 0; i < row.size(); i++ ){
            dataFrame.addValue(columnNames.get(i), row.get(i) );
        }
    }

    public DataFrame loadData(String filename){
        DataFrame dataFrame = new DataFrame();
        CSVFormat format = CSVFormat.DEFAULT;
        ArrayList<String> columnNames = dataFrame.getColumnNames();

        try(Reader reader = new FileReader(filename);
            CSVParser csvParser = new CSVParser(reader,format ))
        {
            for (CSVRecord row: csvParser){
                if (row.getRecordNumber() == 1){
                    // first row in file is column name, so add it first
                    addColumnNames(dataFrame,row);
                }
                else{
                    addValues(dataFrame,row,columnNames);
                }
            }

        }catch (IOException e){
            // how does this work? how do i print smth like no file found?
            //throw new RuntimeException("File not found: " + filename, e);
            e.printStackTrace();
        }
        return dataFrame;
    }
}
