package stepDefinitions;


import helpers.MainHelper;
import helpers.WeatherByCityHelper;
import io.cucumber.datatable.DataTable;
import model.WeatherForCities;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.*;

public class WeatherDataByCity {
    WeatherByCityHelper weatherByCityHelper = new WeatherByCityHelper();
    MainHelper helper = new MainHelper();
    WeatherForCities weatherForCitiesJSON;
    ObjectMapper mapper = new ObjectMapper();
    String weatherResponse;


    @Given("As a frequent flyer, I want to get current weather data for following cities in the world")
    public void as_a_frequent_flyer_i_want_to_get_current_weather_data_for_following_cities_in_the_world(DataTable dataTable) {
        List<String> cities = dataTable.asList();
        Map<String, String> param = weatherByCityHelper.getCitiesIdParameterInRequiredFormat(cities);
        weatherResponse = helper.invokeGetAPI(param);
    }

    @Then("I can verify the weather data for different cities")
    public void i_can_verify_the_weather_data_for_different_cities() throws Throwable{
        weatherForCitiesJSON = helper.convertJsonStringToJson(weatherResponse);
        verifyWeatherDataForMultipleCities(weatherForCitiesJSON);
    }


    @Given("As a frequent flyer, I want to get current weather data for the city at {double}, {double}")
    public void as_a_frequent_flyer_i_want_to_get_current_weather_data_for_the_city_at(double latitude, double longitude) {
        Map<String, String> param = weatherByCityHelper.getCitiesCordinateParameterInRequiredFormat(Double.toString(latitude), Double.toString(longitude));
        weatherResponse = helper.invokeGetAPI(param);
    }
    @Then("I can verify the weather data for a city")
    public void i_can_verify_the_weather_data_for_a_city() throws Throwable{
        weatherForCitiesJSON = helper.convertJsonStringToJson(weatherResponse);
        verifyWeatherDataForACity(weatherForCitiesJSON);
    }

    @Then("I can check for current temperature of the city")
    public void i_can_check_for_current_temperature_of_the_city() {
        Assert.assertNotNull(weatherForCitiesJSON.getData().get(0).app_temp);
    }

    @Then("I do not see weather data")
    public void i_do_not_see_weather_data() {
        Assert.assertEquals(weatherResponse,"");
    }

    private void verifyWeatherDataForMultipleCities(WeatherForCities weatherForCitiesJSON) {
        Assert.assertTrue(weatherForCitiesJSON.count == 3);
        Assert.assertNotNull(weatherForCitiesJSON.getData().get(0).app_temp);
    }

    private void verifyWeatherDataForACity(WeatherForCities weatherForCitiesJSON) {
        int i = weatherForCitiesJSON.count;
        Assert.assertTrue(weatherForCitiesJSON.count == 1);
        Assert.assertNotNull(weatherForCitiesJSON.getData().get(0).app_temp);
    }
}
