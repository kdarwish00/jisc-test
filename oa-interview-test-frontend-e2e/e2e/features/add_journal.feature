Feature: Add journal

  Scenario: Admin adds a new journal article
    Given the user navigates to the page
    And the user enables admin mode
    When the user adds a new journal with author "Test Author" and text "This is a brand new article"
    Then the new journal with text "This is a brand new article" is visible in the list

