@sign_up
Feature: Sign Up
  Scenario: Sign up success
    Given Navigate to Page Registration
    When A User enters name "Fakihatin Wafiyah" email "fakihatinwafiyah24@gmail.com" and password "password"
    And A User clicks on Verify button
    Then Application shows puzzle human verification.

  Scenario: Sign up Failed with invalid email format
    Given Navigate to Page Registration
    When A User enters name "Fakihatin Wafiyah" email "fakihatinwafiyah24gmail.com" and password "password"
    And A User clicks on Verify button
    Then Application show error message below email field.