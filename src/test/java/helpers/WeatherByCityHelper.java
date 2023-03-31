package helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherByCityHelper {
    MainHelper mainHelper = new MainHelper();
    public String  responseBody;

    public Map<String, String> getCitiesIdParameterInRequiredFormat(List<String> cities) {
        Map<String, String> params = new HashMap<String, String>();

        String parameter = new String();

        for (int i=0; i< (cities.size() - 1); i++) {
            parameter = parameter + cities.get(i) + ",";
        }
        parameter = parameter + cities.get(cities.size() - 1);

        params.put("cities", parameter);
        return params;
    }

    public Map<String, String> getCitiesCordinateParameterInRequiredFormat(String latitude, String longitude) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("lat", latitude);
        params.put("lon", longitude);
        return params;
    }



}
