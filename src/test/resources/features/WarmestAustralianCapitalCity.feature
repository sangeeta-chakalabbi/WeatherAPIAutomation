@Regression
Feature: Get warmest Australian capital city

  @WeatherAPIs
  Scenario Outline: Get warmest Australian capital city
    Given Given a list of capital cities of Austrlia in <filePath>
    And I get weather data for every city
    Then I find the warmest city
    Examples:
      | filePath                                                 |
      | "src/test/resources/testData/australiaCapitalCities.csv" |




