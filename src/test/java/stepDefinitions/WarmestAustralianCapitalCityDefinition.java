package stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.WeatherByCityHelper;
import helpers.WarmestCapitalCityHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.WeatherForACity;
import model.WeatherForCities;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarmestAustralianCapitalCityDefinition {
    WeatherByCityHelper weatherByCityHelper = new WeatherByCityHelper();
    WeatherForCities weatherForCitiesJSON;
    WeatherForACity weatherForACityJSON;
    ObjectMapper mapper = new ObjectMapper();
    String weatherResponse;
    String warmestCity;
    Double warmestTemperature = 0.0;

    WarmestCapitalCityHelper warmestCapitalCityHelper = new WarmestCapitalCityHelper();
    List<HashMap<String, String>> csvData;

    @Given("Given a list of capital cities of Austrlia in {string}")
    public void given_a_list_of_capital_cities_of_austrlia_in(String filePath) throws Throwable{
        csvData= warmestCapitalCityHelper.readCSV(filePath);
    }

    @Given("I get weather data for every city")
    public void i_get_weather_data_for_every_city() throws Throwable{
        for(int i=0; i< csvData.size(); i++) {
            Map<String, String> param = warmestCapitalCityHelper.getCityAndCountryParam(csvData.get(i), "AU");
            weatherResponse = weatherByCityHelper.getWeatherResponse(param);
            weatherForCitiesJSON = convertJsonStringToJson(weatherResponse);
            if(weatherForCitiesJSON.getData().get(0).app_temp > warmestTemperature) {
                warmestTemperature = weatherForCitiesJSON.getData().get(0).app_temp;
                warmestCity = weatherForCitiesJSON.getData().get(0).city_name;
            }
        }

    }
    @Then("I find the warmest city")
    public void i_find_the_warmest_city() {
        Assert.assertNotNull(warmestCity);
    }

    private WeatherForCities convertJsonStringToJson(String weatherResponse) throws Throwable{
        ObjectMapper mapper = new ObjectMapper();
        WeatherForCities weatherForCitiesJSON = mapper.readValue(weatherResponse, WeatherForCities.class);
        return weatherForCitiesJSON;
    }

}
