@ui @motor
Feature: Jira-Feature# Used Cars Details Search
  As a test user
  I will search for the  cars in trade me application
  So that I can see the cars list from the search

  @getcarmakecount
  Scenario Outline: Get list of used cars and check if the search result returns any results in UI

    Given user opens TradeMe application
    When user navigated to the <maintab> menu option
    And user navigates to the <dropdown> options
    Then user checks for the total number of <dropdown> options matches <totalMakeNumber>

    Examples:
      | maintab | dropdown   |totalMakeNumber|
      | Motors  | Make       |78             |

    @getusedcarcount
    Scenario Outline: Get list of used cars and check if the search result returns any results in UI

      Given user opens TradeMe application
      When user navigated to the <maintab> menu option
      And user navigates to the <dropdown> options
      Then user logs the total number of cars for
      |Ferrari|
      |BMW    |
      |Mazda  |

      Examples:
        | maintab | dropdown   |
        | Motors  | Make       |

