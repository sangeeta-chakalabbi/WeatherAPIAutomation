package helpers;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReaderHeaderAware;
import io.restassured.response.Response;
import model.WeatherForCities;
import org.apache.log4j.Logger;


public class MainHelper {
	public static Properties prop = new Properties();
    static final Logger log = Logger.getLogger(MainHelper.class);
	Response response ;

	public String invokeGetAPI(Map<String, String> queryParams) {
		String baseUrl = MainHelper.prop.getProperty("url");
		String key = MainHelper.prop.getProperty("key");

		queryParams.put("Key", key);
				response = given()
						.queryParams(queryParams)
				.when()
					.get(baseUrl)
				.then()
				.extract().response();
				return response.getBody().asString();
	}

	public String getPropertyFromConfig(String key) {
		String configFilePath = "./src/test/resources";
		try {
			FileInputStream config = new FileInputStream(configFilePath + "/config.properties");
			prop.load(config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String value = prop.getProperty(key);

		return value;
	}

	public WeatherForCities convertJsonStringToJson(String weatherResponse) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		WeatherForCities weatherForCitiesJSON = mapper.readValue(weatherResponse, WeatherForCities.class);
		return weatherForCitiesJSON;
	}


}
