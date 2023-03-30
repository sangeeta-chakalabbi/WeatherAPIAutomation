package stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.ColdestUsStateHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.WeatherForACity;
import model.WeatherForCities;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColdestUsStateDefinition {
    WeatherForCities weatherForCitiesJSON;
    WeatherForACity weatherForACityJSON;
    ObjectMapper mapper = new ObjectMapper();
    String weatherResponse;
    String coldestState;
    Double pastTemp;
    Double currentTemp;
    Double coldestTemp;
    boolean initializedBefore = false;

    ColdestUsStateHelper coldestUsStateHelper = new ColdestUsStateHelper();
    List<HashMap<String, String>> csvData;

    @Given("Given a list of US States in {string}")
    public void given_a_list_of_us_states_in(String filePath) throws Throwable{
        csvData= coldestUsStateHelper.readCSV(filePath);
    }
    @Given("I get weather data for every state")
    public void i_get_weather_data_for_every_state() throws Throwable{
        currentTemp = pastTemp;
        for(int i=0; i< csvData.size(); i++) {
            Map<String, String> param = coldestUsStateHelper.getStateAndCountryParam(csvData.get(i), "US");
            weatherResponse = coldestUsStateHelper.getWeatherResponse(param);
            weatherForCitiesJSON = convertJsonStringToJson(weatherResponse);
            currentTemp = weatherForCitiesJSON.getData().get(0).app_temp;
            if(initializedBefore == false) {
                coldestTemp = currentTemp;
                initializedBefore = true;
            }

            if(currentTemp < coldestTemp) {
                coldestTemp = currentTemp;
                coldestState = weatherForCitiesJSON.getData().get(0).state_code;
            }
        }
    }
    @Then("I find the coldest state")
    public void i_find_the_coldest_state() {
        Assert.assertNotNull(coldestState);
    }

    private WeatherForCities convertJsonStringToJson(String weatherResponse) throws Throwable{
        ObjectMapper mapper = new ObjectMapper();
        WeatherForCities weatherForCitiesJSON = mapper.readValue(weatherResponse, WeatherForCities.class);
        return weatherForCitiesJSON;
    }
}
