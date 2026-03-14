package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.List;

public class DataFrame {
    private ArrayList<Column> columns = new ArrayList<>();

    public void addColumn(String name)
    {
        Column newColumn = new Column(name);
        columns.add(newColumn);
    }

    private Column getColumn(String targetColumnName)
    {
        for (Column column: columns)
        {
            if (column.getName().equals(targetColumnName))
            {
                return column;
            }
        }
        throw new  IllegalArgumentException("Column not found" + targetColumnName);
    }

    public List<String> getColumnNames(){
        List<String> names = new ArrayList<>();
        for (Column column:columns)
        {
            names.add(column.getName());
        }
        return names;
    }

    public int getRowCount()
    {
        if(columns.isEmpty()){
            return 0;}
        return columns.get(0).getSize();
    }

    public String getValue(String columnName, int row) {
        return getColumn(columnName).getRowValue(row);
    }

    public void putValue(String columnName, int row, String value) {
         getColumn(columnName).setRowValue(row,value);
    }

    public void addValue(String columnName, String value){
        getColumn(columnName).addRowValue(value);
    }
}
