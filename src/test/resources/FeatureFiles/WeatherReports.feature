@Weather
Feature: Acceptance testing to validate Weather report from 2 different resource are same.
  In order to validate that
  Weather report from 2 different resource are same
  Doing the Acceptance Testing

  @Weather
  Scenario: UI get weather report of city chennai
    Given I am on the Home Page "https://www.ndtv.com//" of ndtv Website
    When I move to Car For Sale Menu
    And click on city
    | Chennai     |
    Then get the temperature of selected city


  @Weather
  Scenario: API get weather report of city chennai
    Given I hit the get endpoint of weather report
      | Chennai     |
      | 7fe67bf08c80ded756e598d6f8fedaea         |
    Then get the temperature from result and store

  Scenario:Compare result of Scenario1 output and Scenario2 output
    Given I compare the result of two scenario outputs
    Then Verify the results are same