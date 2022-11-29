@api @carsapi
Feature: JiraId-feature#  Cars Search via Services
  As a test user
  I want to search for the cars in trade me sandbox application
  So that I can see for any cars are available

  @getusedcarcount
  Scenario Outline: Get list of used cars and check if the search result returns any results

    Given Business details information for tradeMe
    When the user searches for the data in <apiName>
    Then there is total <listing> in the TradeMe UsedCars category

    Examples:
      | apiName     | listing  |
      | getCars     | 78       |

