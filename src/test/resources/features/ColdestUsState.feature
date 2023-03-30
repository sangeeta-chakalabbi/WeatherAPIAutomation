@Regression
Feature: Get coldest US state

  @WeatherAPIs
  Scenario Outline: Get coldest US state
    Given Given a list of US States in <filePath>
    And I get weather data for every state
    Then I find the coldest state
    Examples:
      | filePath                                 |
      | "src/test/resources/testData/states.csv" |




