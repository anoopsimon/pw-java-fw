
Feature: Playwright Tests

  Scenario: Verify playwirght ui
    Given I navigate to Playwright website
    And I verify search
    And I read test data from json
    And I read test data from excel

@data
  Scenario Outline: Verify playwright ui
    And I read test data from excel
    Examples:
    |id|
    |1|
    |2|