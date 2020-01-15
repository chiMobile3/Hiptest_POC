# Ryan
# Account: 104877

Feature: Edit Modifier Set Page
  As a user of the merchant portal
  I want to make edits to my modifier sets
  So that I can make them more customizable

  Background: 
#    Given User sees POS Portal
#     When User enters UserName and Password
    Given User sees POS Portal
    When User enters UserName "RDAUGHERTY065"
    And User clicks Log in Button
    Then User should see the homepage

  # Done
  Scenario: The user can successfully navigate back to the Modifier Sets page
    Given the user is on the "ModifierSets" page
    When the user clicks the 'Edit' icon next to the "TestModifierSet" modifier
    And the user clicks the "Done" button
    Then the user is directed to the "Modifier Sets" page

  # Save
  Scenario: The user can successfully save edits to a modifier set
    Given the user is on the "ModifierSets" page
    When the user clicks the 'Edit' icon next to the "TestModifierSet" modifier
    And the user Selects the "Peach" in the to "Color"  dropdown
    And the user clicks the "Save" button
    Then a toast message is displayed on the page
    """
    Saved Modifier Set
    """
    Then the user is directed to the "Edit Modifier Set" page

  # Add This Modifier Set To These Items
  Scenario: The user can select items that will utilize a modifier set
    Given the user is on the "ModifierSets" page
    When the user clicks the 'Edit' icon next to the "TestModifierSet" modifier
    And the user clicks the "Add" button  for the "Add This Modifier Set To These Items" panel
    And the user expands the "DESSERTS" checkbox list
    And the user "Stores" the "DESSERTS" items listed
    And the user checks the "DESSERTS" checkbox
    And the user clicks the "Save" button
    Then all items from the "DESSERTS" category will be listed in the "Add This Modifier Set To These Items" panel

  # Add This Modifier Set To These Items
  Scenario: The user can delete a modifier set from an item
    Given the user is on the "ModifierSets" page
    When the user clicks the 'Edit' icon next to the "DeleteModifierTest" modifier
    When the "Apple Juice" item is present
    And the user clicks the Delete icon for "Apple Juice" modifier set item
    And the user clicks the "OK" button
    Then the "Apple Juice" item is not present at the Add This Modifier Set Items panel
    #Then "Apple Juice" will be deleted from the "Add This Modifier Set To These Items" panel

  # Add the Apple Juice back after deletion.....
  Scenario: The user adds item back after deletion
    Given the user is on the "ModifierSets" page
    When the user clicks the 'Edit' icon next to the "DeleteModifierTest" modifier
    Then the "Apple Juice" item is not present at the Add This Modifier Set Items panel
    And the user clicks the "Add" button  for the "Add This Modifier Set To These Items" panel
    And the user expands the "DRINKS" checkbox list
    And the user expands the "DRINKS > SPECIALTY" checkbox list sub-item and checks the "Apple Juice" checkbox
    And the user clicks the "Save" button

  # Add This Modifier Set To These Items
  Scenario: The user can enter an override price for an item that uses a modifier set
    Given the user is on the "ModifierSets" page
    When the user clicks the 'Edit' icon next to the "DeleteModifierTest" modifier
    And the user enters "2.00" in the "Override Price" field
    And the user clicks the "Save" button
    Then a toast message is displayed on the page
    """
    Modifier Saved
    """
  
  # Modifiers
  Scenario: The user can add a modifier to be used within a modifier set
    Given the user is on the "ModifierSets" page
    When the user clicks the 'Edit' icon next to the "ModifierToModifierSetTest" modifier
    And the user checks the "TestModifier1" checkbox
    And the user clicks the "Save" button
    Then a toast message is displayed on the page
    """
    Modifier Saved
    """
  
  # Modifiers
  Scenario: The user can set an override price for a modifier that is used within a modifier set
    Given the user is on the "Modifier Sets" page
    When the user clicks the 'Edit' icon next to the "ModifierToModifierSetTest" modifier
    And the user checks the "TestModifier2" checkbox
    And the user inputs "1.00" in the "Override Price" field
    And the user clicks the "Save" button
    Then a toast message is displayed on the page
    """
    Modifier Saved
    """
  
  # Items Used As Modifier
  Scenario: The user can set an override price for a modifier that is used within a modifier set
    Given the user is on the "ModifierSets" page
    When the user clicks the 'Edit' icon next to the "ItemsAsModifiersSet" modifier
    And the user clicks the "Add" button under the "Items Used As Modifiers" section
    And the user checks the "Drinks" checkbox
    Then all items from the "Drinks" category will be listed in the "Items Used As Modifiers" panel
  
  # Items Used As Modifier
  Scenario: The user can delete an item from a modifier set
    Given the user is on the "ModifierSets" page
    When the user clicks the 'Edit' icon next to the "DeleteItemsFromModifierSet" modifier
    And the user clicks the "Delete" icon for "Blackened Chicken" in the "Items Used As Modifiers" panel
    And the user clicks the "OK" button
     #Then "Blackened Chicken" will be deleted from the "Items Used As Modifiers" panel
  
  # Items Used As Modifier
  Scenario: The user can enter an override price for an item that's used as a modifier in a modifier set
    Given the user is on the "ModifierSets" page
    When the user clicks the 'Edit' icon next to the "DeleteItemsFromModifierSet" modifier
    And the user enters "2.00" in the "Override Price" field
    And the user clicks the "Save" button
    Then a toast message is displayed on the page
    """
    Modifier Saved
    """
    And the user is on the "Modifier Sets" page
  
  
