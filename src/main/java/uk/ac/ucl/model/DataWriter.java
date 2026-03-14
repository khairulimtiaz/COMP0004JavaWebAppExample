package uk.ac.ucl.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataWriter
{
    public void writeData(DataFrame dataFrame, String filename)
    {
        try (FileWriter writer = new FileWriter(filename))
        {
            List<String> columnNames = dataFrame.getColumnNames();

            // Write header row
            writer.write(String.join(",", columnNames));
            writer.write("\n");

            // Write each data row
            for (int row = 0; row < dataFrame.getRowCount(); row++)
            {
                List<String> rowValues = new ArrayList<>();
                for (String column : columnNames)
                {
                    rowValues.add(dataFrame.getValue(column, row));
                }
                writer.write(String.join(",", rowValues));
                writer.write("\n");
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to write data to file: " + filename, e);
        }
    }
}