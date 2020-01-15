# Lindsay

Feature: Search Items in Item Library
This set of tests will validate an account level user can search items by item name, item code and also filter the search results by category

# The account is already set up with an item library

  Background:
    Given User sees POS Portal
    When User enters UserName and Password
      | UserName                         | Password | 
      | lchurch_automation1@imobile3.com | !Password12 |
      And User clicks Log in Button
     Then User should see the homepage    
  
  # Search
  Scenario: Search by entire Item Name
    Given the user is on the "ItemLibrary" page
     When the user searches "pc repair" in the "Item Name/Code" field
     And the user clicks the "Search" button
     Then The category "Services" will display in Item Library page
    And the only item listed in the search results is "PC Repair"
  
  # Search
  Scenario: Search by entire Item Code
    Given the user is on the "ItemLibrary" page
     When the user enters "123456" in the "Item Name/Code" field
      And the user clicks the "Search" button
     Then The category "T-Mobile Electronics" will display in Item Library page
      And the only item listed in the search results is "Apple AirPods"