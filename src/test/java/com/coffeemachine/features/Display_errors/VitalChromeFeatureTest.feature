Feature: Beans
    As a coffee lover
    I have to put fresh beans from time to time
    So I can have coffee when I need it

  Background:
    Given the user is on Vital
    And the user logs in a "rdaugherty065@imobile3.com" with a password of "Password1!"


  Scenario: Logging in to Vital and Clicking Link
    # You keep getting coffee even if the "Empty grounds" message is displayed. That said it's not a fantastic idea, you'll get ground everywhere when you'll decide to empty it.

    When the user Clicks the POS link
    Then the user shall be at the POS Dashboard
    And the following 6 dashboard panels shall be listed "Sales", "Discounts", "Net Sales", "Taxes & Fees", "Tips", "Total Sales"
