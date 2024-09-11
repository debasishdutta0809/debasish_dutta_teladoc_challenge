Feature: Web Table Automation

  Scenario: Add a user and validate the user has been added to the table
    Given I am on the web table page
    When I add a user with details "John", "Doe", "john@example.com", "30"
    Then I should see the user "John" in the table

  Scenario: Delete the user "novak" from the table
    Given I am on the web table page
    When I delete the user "novak"
    Then I should not see the user "novak" in the table
