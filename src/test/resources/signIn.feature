# new feature
# Tags: optional

@sign_in
Feature: A description

  Scenario: Sign in success using email
    Given Navigate to Page Sign In
    When A User enter email "yasminallana24@gmail.com"
    And A User clicks on Continue button
    And A User enter password "rumahcantik24"
    And A User clicks on Sign In button
    Then Directed to shoping page with initial user as "Hello, Yasmin"

  Scenario: Sign in Failed using unregistered email
    Given Navigate to Page Sign In
    When A User enter email "yasminallana@gmail.com"
    And A User clicks on Continue button
    Then Show error message "We cannot find an account with that email address"

  Scenario: Sign in Failed using invalid password
    Given Navigate to Page Sign In
    When A User enter email "yasminallana24@gmail.com"
    And A User clicks on Continue button
    And A User enter password "rumahcantik"
    And A User clicks on Sign In button
    Then Show error message "Your password is incorrect"