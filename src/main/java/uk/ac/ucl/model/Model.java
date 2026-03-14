package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashMap;

public class Model {
  private DataFrame dataFrame;

  public void loadData(String filename) {
    DataLoader loader = new DataLoader();
    dataFrame = loader.loadData(filename);
  }

  private Map<String,String> getOneSummary(int row)
  {
    Map<String,String> onePSummary = new HashMap<>();

    String name = dataFrame.getValue("PREFIX", row) + " "
            + dataFrame.getValue("FIRST", row) + " "
            + dataFrame.getValue("LAST", row) + " "
            + dataFrame.getValue("SUFFIX", row);

    String birthdate = dataFrame.getValue("BIRTHDATE", row);
    String deathdate = dataFrame.getValue("DEATHDATE", row);
    String gender = dataFrame.getValue("GENDER", row);
    String id = dataFrame.getValue("ID", row);

    String status = deathdate.isEmpty() ? "Alive" : "Deceased";

    onePSummary.put("NAME", name);
    onePSummary.put("BIRTHDATE", birthdate);
    onePSummary.put("DEATHDATE", deathdate);
    onePSummary.put("STATUS", status);
    onePSummary.put("GENDER", gender);
    onePSummary.put("ID", id);

    return onePSummary;
  }

  public List<Map<String,String>> getPatientsSummary() {
    List<Map<String,String>> patientsSummary = new ArrayList<>();
    for (int row = 0; row < dataFrame.getRowCount(); row++) {
      patientsSummary.add(getOneSummary(row));
    }
    return patientsSummary;
  }

  public Map<String,String> getPatientById(String id) {
    for (int row = 0; row < dataFrame.getRowCount(); row++) {
      if (dataFrame.getValue("ID", row).equals(id)) {
        return getPatientInfo(row);
      }
    }
    throw new IllegalArgumentException("Patient not found: " + id);
  }

  private Map<String,String> getPatientInfo(int row)
  {
    Map<String,String> patient = new HashMap<>();
    for (String column : dataFrame.getColumnNames()) {
      patient.put(column, dataFrame.getValue(column, row));
    }
    String status = patient.get("DEATHDATE").isEmpty() ? "Alive" : "Deceased";
    patient.put("STATUS", status);
    return patient;
  }

  public List<Map<String,String>> searchFor(String keyword) {
    List<Map<String,String>> matchedList = new ArrayList<>();
    //allow search with lower/upper case
    String lowerKeyword = keyword.toLowerCase();
    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      for (String colName : dataFrame.getColumnNames())
      {
        // search until matched or partially matched
        if (dataFrame.getValue(colName, row).toLowerCase()
                .contains(lowerKeyword))
        {
          matchedList.add(getOneSummary(row));
          //break to avoid repeated results
          break;
        }
      }
    }
    return matchedList;
  }


  //operations

  private int calculateAge(String birthdate)
  {
    LocalDate birth = LocalDate.parse(birthdate);
    return Period.between(birth, LocalDate.now()).getYears();
  }

  public Map<String,String> getOldestAlivePatient()
  {
    int oldestRow = -1;
    int oldestAge = -1;

    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      if (dataFrame.getValue("DEATHDATE", row).isEmpty())
      {
        int age = calculateAge(dataFrame.getValue("BIRTHDATE", row));
        if (age > oldestAge)
        {
          oldestAge = age;
          oldestRow = row;
        }
      }
    }

    if (oldestRow == -1)
    {
      throw new IllegalStateException("No alive patients found");
    }

    Map<String,String> result = getOneSummary(oldestRow);
    result.put("AGE", String.valueOf(oldestAge));
    return result;
  }

  public Map<String,Integer> getAliveDeceasedCount()
  {
    int alive = 0;
    int deceased = 0;
    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      if (dataFrame.getValue("DEATHDATE", row).isEmpty())
      {
        alive++;
      }
      else
      {
        deceased++;
      }
    }
    Map<String,Integer> result = new LinkedHashMap<>();
    result.put("Alive", alive);
    result.put("Deceased", deceased);
    return result;
  }

  public Map<String,Integer> getCountByCity()
  {
    Map<String,Integer> cityCount = new LinkedHashMap<>();
    for (int row = 0; row < dataFrame.getRowCount(); row++)
    {
      String city = dataFrame.getValue("CITY", row);
      cityCount.put(city, cityCount.getOrDefault(city, 0) + 1);
    }
    return cityCount;
  }

}
