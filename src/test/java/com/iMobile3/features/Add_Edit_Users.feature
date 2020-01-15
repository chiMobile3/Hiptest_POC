# Lindsay

Feature: Add and Edit Users
  This set of tests will validate a user of a single location account can add, edit and search users

  #Background:
   # Given the user is logged in to a TSYS Agency SUO account (105094)
  
   #   | Email                            | Password |
    #  | lchurch_automation1@imobile3.com | !Password1 |
  
    #  And the user is on the "Users" page
  
  # Add User - User Details # Add User Button
  @JenkinsTest @SUOAccount105094-UsersPage
  Scenario: Validate First Name and Last Name are required fields on Add User page
    When the user clicks the "Add User" Button
    And the user clicks the "Save" button
    Then the following fields are highlighted with a red box
      | First Name |
      | Last Name  |
    And the following error messages are displayed in red font for the required fields
      | The First Name field is required. |
      | The Last Name field is required.  |



  # Add User - User Details # Add User Button
  @JenkinsTest @SUOAccount105094-UsersPage
  Scenario: Validate Username and Password are required fields on Add User page
    When the user clicks the "Add User" button
    And the user populates the listed fields with the following information:
      | First Name | Andy  |
      | Last Name  | Admin |
    And the user clicks the "Save" button
    Then the following fields are highlighted with a red box
      | Username |
      | Password |
    And the following error messages is displayed in red font for the required fields
      | Passwords must be at least 8 characters and contain at least one uppercase, one lowercase, one number, and one special character. |
    And a bootstrap alert is displayed at the top of the page
      """
      ERRORS HAVE OCCURRED
      The Password field is required.
      Username is required.
      """


  # Add User - Save # Add User Button
  @JenkinsTest @SUOAccount105094-UsersPage
  Scenario: Successfully add a User
    When the user clicks the "Add User" button
    And the user populates the listed fields with the following information:
      | First Name       | Andy     |
      | Last Name        | Admin    |
      | Username         | andy3     |
      | Password         | Test123! |
      | Confirm Password | Test123! |
    And the user clicks the "Save" button
    Then a toast message is displayed on the page that states:
    And the user is directed to the "Edit User" page
    #Need tear down to Remove this user from the database
  
  # Group Assignment # Edit User Icon
  Scenario: Assign a Group to a User
    When the user clicks the "edit" icon for any given user
    And the user selects the checkbox next to 'Name' on the 'Groups' panel
    And the user clicks the 'Save' button
    Then all checkboxes are checked
    And a "Saved Group Assignments" message is displayed
    And the user remains on the "Edit User" page
  
  # Change PIN Button # Edit User Icon # Generate Button
  Scenario: Generate a PIN
    When the user clicks the 'Edit' icon next to any user with the name "andy"
    And the user clicks the 'Change PIN' button
    And the user clicks the 'Generate' button on the "Change PIN" prompt
    And the user clicks "Save"
    Then a "Saved PIN" message is displayed
    And the user remains on the "Edit User" page
  
  # Change PIN Button # Edit User Icon
  Scenario: Change PIN
    When the user clicks the 'Edit' icon next to any user with the name "andy"
    And the user clicks the 'Change PIN' button
    And the user enters "1234" in the 'PIN' field
    And the user clicks 'Save'
    Then a "Saved PIN" message is displayed
    And the user remains on the "Edit User" page
  
  # Change PIN Button # Edit User Icon
  Scenario: Validate the Cancel button on the Change PIN prompt returns user to the Edit User page
    When the user clicks the 'Edit' icon next to any username
    And the user clicks the 'Change PIN' button
    And the user clicks the 'Cancel' button on the "Change PIN" prompt
    Then the "Change PIN" prompt is dismissed
    And the user remains on the "Edit User" page
  
  # Change Password button # Edit User Icon
  Scenario: Change Password
    When the user clicks the 'Edit' icon next to any user with the name "andy"
    And the user clicks  the 'Change Password' button
    And the user enters the following information

      | Password  | Confirm Password |
      | P@ssword1 | P@ssword1        |

    And the user clicks the 'Save' button
    Then a "Saved Password" message is displayed
    And the user remains on the "Edit User" page
 
  # Change Password button # Edit User Icon
  Scenario: Validate the Cancel button on the Change Password prompt returns user to the Edit User page
    When the user clicks the 'Edit' icon next to any user with the name "andy"
    And the user clicks  the 'Change Password' button
    And the user clicks the 'Cancel' button on the "Change Password" prompt
    Then the "Change Password" prompt is dismissed
    And the user remains on the "Edit User" page
 
  # Edit User - User Details # Edit User Icon
  Scenario: Make a user Inactive
    When the user clicks the 'Edit' icon next to username "andy"
    And the user selects the 'Active' checkbox
    And the user clicks the 'Save' button
    Then a "Saved User" message is displayed
    And the "Active" checkbox is unchecked
    And the "Groups" section is no longer displayed
  
  # Edit User - User Details # Edit User Icon
  Scenario: Make a user Active
    When the user clicks the 'Edit' icon next to username "andy"
    And the user selects the 'Active' checkbox
    And the user clicks the 'Save' button
    Then a "Saved User" message is displayed
    And the "Active" checkbox is checked
    And the "Groups" section is displayed
      
      
  # Groups Assignment # Edit User Icon
  Scenario: Remove a Group from a User
    When the user clicks the 'Edit' icon next to username "andy"
    And the user clicks checkboxes next to Group Names that are currently checked
    And the user clicks the 'Save' button
    Then a "Saved Group Assignments" message is displayed
    And the clicked checkboxes are now unchecked
    And the user remains on the "Edit User" page
      
  # Done # Edit User Icon
  Scenario: Validate the Done button on the Edit User page returns user to the Users page
    When the user clicks the 'Edit' icon next to username "andy"
    And the user clicks the 'Done' button
    Then the user is returned to the "Users" page
  
  # Cancel
  Scenario: Validate the Cancel button on the Add User page returns user to the Users page
    When the user clicks the 'Add User' button
    And the user clicks the 'Cancel' button
    Then the user is returns to the "Users" page
  
  # Add User - User Details
  Scenario: Validate a duplicate User cannot be created
    When the user clicks the 'Add User' button
    And the user enters the following information

      | First Name | Last Name | Username | Password | Confirm Password |
      | Andy       | Admin     | andy     | Test123! | Test123!         |

    And the user clicks the 'Save' button
    Then a red bootstrap is displayed above the "User Details" panel
  
  """
  ERRORS HAVE OCCURRED
  
  A user with the username "andy" already exists.
  
  """
  
  # Search
  Scenario: Search User by Username
    When the user enters "andy" in the "Username" field
    And the user clicks the 'Search' button
    Then "andy" is the only user displayed in the users list
  
  # Search
  Scenario: Search User by Name
    When the user enters "Bartender" in the "Username" field
    And the user clicks the 'Search' button
    Then "bob" is the only user displayed in the users list
  
  # Search
  Scenario: Search User by Email
    When the user enters "andy@admin" in the "Username" field
    And the user clicks the 'Search' button
    Then "andy" is the only user displayed in the users list
  
  # Search
  Scenario: Search User by Partial Name
    When the user enters "bo" in the "Username" field
    And the user clicks the 'Search' button
    Then "bob" is the only user displayed in the users list
     
  # Search
  Scenario: Filter user by Status
    When the user clicks the 'All Users' dropdown
    And the user clicks the "Inactive" status
    And the user clicks the 'Search' button
    Then the "inactive" user is displayed
  
  # Export All Search Results
  Scenario: Export All Search Results
    When the user clicks "Export All Search Results"
    Then a .csv file is downloaded

  # Export Listed Search Results
  Scenario: Export Listed Search Results
    When the user clicks "Export Listed Search Results"
    Then a .csv file is downloaded

  
  
