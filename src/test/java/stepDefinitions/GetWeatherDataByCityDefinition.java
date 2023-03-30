package stepDefinitions;

import helpers.GetWeatherByCityHelper;
import io.cucumber.datatable.DataTable;
import model.WeatherForCities;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

public class GetWeatherDataByCityDefinition  {
    GetWeatherByCityHelper getWeatherByCityHelper = new GetWeatherByCityHelper();
    WeatherForCities weatherForCitiesJSON;
    ObjectMapper mapper = new ObjectMapper();
    String weatherResponse;


    @Given("As a frequent flyer, I want to get current weather data for following cities in the world")
    public void as_a_frequent_flyer_i_want_to_get_current_weather_data_for_following_cities_in_the_world(DataTable dataTable) {
        List<String> cities = dataTable.asList();
        Map<String, String> param = getWeatherByCityHelper.getCitiesIdParameterInRequiredFormat(cities);
        weatherResponse = getWeatherByCityHelper.getWeatherResponse(param);
    }

    @Then("I can verify the weather data for different cities")
    public void i_can_verify_the_weather_data_for_different_cities() throws Throwable{
        weatherForCitiesJSON = convertJsonStringToJson(weatherResponse);
        verifyWeatherDataForMultipleCities(weatherForCitiesJSON);
    }


    @Given("As a frequent flyer, I want to get current weather data for the city at {double}, {double}")
    public void as_a_frequent_flyer_i_want_to_get_current_weather_data_for_the_city_at(double latitude, double longitude) {
        Map<String, String> param = getWeatherByCityHelper.getCitiesCordinateParameterInRequiredFormat(Double.toString(latitude), Double.toString(longitude));
        weatherResponse = getWeatherByCityHelper.getWeatherResponse(param);
    }
    @Then("I can verify the weather data for a city")
    public void i_can_verify_the_weather_data_for_a_city() throws Throwable{
        weatherForCitiesJSON = convertJsonStringToJson(weatherResponse);
        verifyWeatherDataForACity(weatherForCitiesJSON);
    }


    private WeatherForCities convertJsonStringToJson(String weatherResponse) throws Throwable{
        ObjectMapper mapper = new ObjectMapper();
        WeatherForCities weatherForCitiesJSON = mapper.readValue(weatherResponse, WeatherForCities.class);
        return weatherForCitiesJSON;
    }

    private void verifyWeatherDataForMultipleCities(WeatherForCities weatherForCitiesJSON) {
        int i = weatherForCitiesJSON.count;
        Assert.assertTrue(weatherForCitiesJSON.count == 3);
    }

    private void verifyWeatherDataForACity(WeatherForCities weatherForCitiesJSON) {
        int i = weatherForCitiesJSON.count;
        Assert.assertTrue(weatherForCitiesJSON.count == 1);
    }

}
