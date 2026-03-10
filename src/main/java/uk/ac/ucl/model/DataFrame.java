package uk.ac.ucl.model;

import java.util.ArrayList;

public class DataFrame {
    private ArrayList<Column> columns = new ArrayList<>();

    public void addColumn(String name)
    {
        Column newColumn = new Column(name);
        columns.add(newColumn);
    }

    public ArrayList<String> getColumnNames(){
        ArrayList<String> names = new ArrayList<>();
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

    public String getValue(String columnName, int row)
    {
        for (Column column : columns)
        {
            if (column.getName().equals(columnName))
            {
                return column.getRowValue(row);
            }
        }
        throw new  IllegalArgumentException("Column not found" + columnName);
    }

    public void putValue(String columnName, int row, String value){
        for (Column column:columns)
        {
            if (column.getName().equals(columnName)){
                column.setRowValue(row,value);
                break;
            }
        }
        throw new  IllegalArgumentException("Column not found" + columnName);
    }

    public void addValue(String columnName, String value){
        for (Column column:columns)
        {
            if (column.getName().equals(columnName)){
                column.addRowValue(value);
                break;
            }
            throw new  IllegalArgumentException("Column not found" + columnName);
        }
    }
}
