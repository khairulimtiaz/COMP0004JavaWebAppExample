package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
  private DataFrame dataFrame = new DataFrame();

  public void loadData(String filename) {
    DataLoader loader = new DataLoader();
    dataFrame = loader.loadData(filename);
  }

  public List<String> getPatientNames() {
    List<String> patientNames = new ArrayList<>();

    for (int row = 0; row < dataFrame.getRowCount(); row++) {
      String name = dataFrame.getValue("PREFIX", row) + " "
              + dataFrame.getValue("FIRST", row) + " "
              + dataFrame.getValue("LAST", row) + " "
              + dataFrame.getValue("SUFFIX", row);
      patientNames.add(name);
    }

    return patientNames;
  }

  private List<String> getPatientInfo(int row)
  {
    List<String> matchedPatient = new ArrayList<>();

    for (String column : dataFrame.getColumnNames())
    {
      matchedPatient.add(dataFrame.getValue(column, row));
    }

    return matchedPatient;
  }

  public List<List<String>> searchFor(String keyword) {
    List<List<String>> matchedList = new ArrayList<>();
    //allow search with any case
    String lowerKeyword = keyword.toLowerCase();
    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      for (String colName : dataFrame.getColumnNames())
      {
        // search until matched or partially matched// maybe remove?
        if (dataFrame.getValue(colName, row).toLowerCase()
                .contains(lowerKeyword))
        {
          matchedList.add(getPatientInfo(row));
          //break to avoid repeated results
          break;
        }
      }
    }
    return matchedList;
  }
}
