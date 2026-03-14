package uk.ac.ucl.model;

import java.util.ArrayList;

public class Column {
    private String name;
    private ArrayList<String> rows = new ArrayList<>();

    public Column(String name)
    {
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public int getSize(){
        return rows.size();
    }

    public String getRowValue(int rowNum){
        return rows.get(rowNum);
    }

    public void setRowValue(int rowNum, String values)
    {
        rows.set(rowNum,values);
    }

    public void addRowValue(String values){
        rows.add(values);
    }

    public void removeRow(int rowNum)
    {
        rows.remove(rowNum);
    }

}
