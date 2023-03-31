package helpers;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColdestUsStateHelper {
    MainHelper mainHelper = new MainHelper();
    public String  responseBody;

    public List<HashMap<String, String>> readCSV(String myFile) throws Throwable {
        CSVReader reader = new CSVReaderBuilder(new FileReader(myFile)).build();
        List<String[]> myEntries = reader.readAll();

        String[] keys = myEntries.get(0);
        List<HashMap<String, String>> dataEntries = new ArrayList<HashMap<String, String>>();

        for(int i=1; i<myEntries.size()-1; i++) {
            String[] rowData = myEntries.get(i);

            if(rowData[2].equals("US")) {
                HashMap<String, String> row = new HashMap<String, String>();
                for(int j=0; j<keys.length; j++) {
                    row.put(keys[j], rowData[j]);
                }
                dataEntries.add(row);
            }
        }

        return dataEntries;
    }

    public Map<String, String> getStateAndCountryParam(HashMap<String, String> row, String countryCode) {
        Map<String, String> params = new HashMap<String, String>();

        String stateParam = row.get("state_name");
        params.put("city",stateParam);
        params.put("country",countryCode);

        return params;
    }
}
