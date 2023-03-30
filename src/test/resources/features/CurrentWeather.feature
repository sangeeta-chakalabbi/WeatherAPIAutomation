@Regression
Feature: Get current weather data for city/cities

  @WeatherAPIs
  Scenario: Get weather data for multiple cities based on city ids
    Given As a frequent flyer, I want to get current weather data for following cities in the world
      | 4487042 |
      | 4494942 |
      | 4504871 |
    Then I can verify the weather data for different cities
    And I can check for current temperature of the city

  @Smoke
  Scenario: Get weather data for a city given its co-ordinates
    Given As a frequent flyer, I want to get current weather data for the city at -33.865143, 151.209900
    Then I can verify the weather data for a city
    And I can check for current temperature of the city




