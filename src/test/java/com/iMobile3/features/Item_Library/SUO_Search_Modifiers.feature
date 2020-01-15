# Author: Todd

Feature: SUO_Search_Modifiers

  As a user I should be able to search for modifiers

  Test portal TSYS account 102746
  Background:
    Given User sees POS Portal
    When User enters UserName and Password
      | UserName               | Password |
      | anieves+104934@imobile3.com | Qatest4! |
    And User clicks Log in Button
    Then User should see the homepage
# Search Modifiers - Search for modifier by name

  Scenario: User should be able to search for a modifiers by name
    Given the user is on the "Modifiers" page
    When the user enters "Cheese" in the "Modifier Name" placeholder field
    And the user clicks the "Search" button
    Then "Cheese" will be displayed in the "Name" Column