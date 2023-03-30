package helpers;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarmestCapitalCityHelper {

    public List<HashMap<String, String>> readCSV(String myFile) throws Throwable {
        CSVReader reader = new CSVReaderBuilder(new FileReader(myFile)).build();
        List<String[]> myEntries = reader.readAll();

        String[] keys = myEntries.get(0);
        List<HashMap<String, String>> dataEntries = new ArrayList<HashMap<String, String>>();
        //HashMap<String, String> row = new HashMap<String, String>();
        int k=0;
        for(int i=1; i<myEntries.size()-1; i++) {
            String[] rowData = myEntries.get(i);
            HashMap<String, String> row = new HashMap<String, String>();
            for(int j=0; j<keys.length; j++) {
                row.put(keys[j], rowData[j]);
            }
            dataEntries.add(k++, row);
        }

        return dataEntries;
    }

    public Map<String, String> getCityAndCountryParam(HashMap<String, String> row, String countryCode) {
        Map<String, String> params = new HashMap<String, String>();

        String cityParam = row.get("capital_city");
        params.put("city",cityParam);
        params.put("country",countryCode);

        return params;
    }
}
