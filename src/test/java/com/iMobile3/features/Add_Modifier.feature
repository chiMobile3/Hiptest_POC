Feature: Add Modifier
	As a merchant, I want to add a modifier, so that I can make my menu more customizable
    
    # User is logged in as an account level user
    # After feature is successfully run, delete the modifier from the db
    

    Background:
        Given User sees POS Portal
        When User enters UserName and Password
            | UserName               | Password |
            | anieves+104934@imobile3.com | Qatest4! |
        And User clicks Log in Button
        Then User should see the homepage


    @JenkinsTest
    Scenario: Add a Modifier with a price
        Given the user is on the "Modifiers" page
        When the user clicks the "Add Modifier" button
        And the user enters "Modifier3" in the 'Name' field
        And the user enters "1.25" in the 'Price' field
        When the user clicks the "Save" button
        Then a toast message is displayed on the page
        """
        Saved Modifier
        """
        And the user is directed to the "Edit Modifier" page
        And "Modifier" with a price of "1.25" is displayed


    @JenkinsTest
    Scenario: Validate Name field is required
        Given the user is on the "Modifiers" page
        When the user clicks the "Add Modifier" button
        And the user clicks the "Save" button
        Then the "Name" field is highlighted in a red box
        And an error message is displayed under the "Name" field that states
        """
        The Name field is required.
        """

    @JenkinsTest
    Scenario: Validate Price can be blank
        Given the user is on the "Modifiers" page
        When the user clicks the "Add Modifier" button
		And the user enters "ModifierTest" in the 'Name' field
        And the user clicks the "Save" button
        Then a toast message is displayed on the page
        """
        Saved Modifier
        """
        And the user is directed to the "Edit Modifier" page
        And "ModifierTest" with a price of "0.00" is displayed

    @JenkinsTest
    Scenario: Validate price can be 0.00
    	When the user clicks the "Add Modifier" button
        And the user enters "Modifiertest2" in the 'Name' field
        And the user enters "0.00" in the 'Price' field
        And the user clicks the "Save" button
        Then a toast message is displayed on the page
        """
        Saved Modifier
        """
        And the user is directed to the "Edit Modifier" page
        And "Modifiertest2" with a price of "0.00" is displayed


    @JenkinsTest
    Scenario: Validate Time of Use Checkbox disables price field
    	When the user clicks the "Add Modifier" button
        And the user enters "Modifiertest3" in the 'Name' field
        And the user enters "2.50" in the 'Price' field
        And the user enables the "Enter modifier amount at time of use" checkbox
        Then "2.50" is removed from the 'Price' field
        And the 'Price' field is disabled


    @JenkinsTest
    Scenario: Validate Cancel Button Returns User to 'Modifiers' page
    	When the user clicks the "Add Modifier" button
        And the user clicks the "Cancel" button
        Then the user is returned to the 'Modifiers' page
        
        
    Scenario: Add an Inactive Modifier
    	When the user clicks the "Add Modifier" button
        And the user disables the "Active" checkbox
        And the user enters "Modifiertest3" in the 'Name' field
        And the user enters ".50" in the 'Price' field
        And the user clicks the "Save" button
        Then a toast message is displayed on the page
        """
        Saved Modifier
        """
        And the user is directed to the "Edit Modifier" page
        And "Modifiertest3" with a price of "0.50" is displayed
        And the "Active" checkbox is unchecked

        
        
 



    
   
 

 




