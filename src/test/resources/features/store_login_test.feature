Feature: User login

  Scenario Outline: login flow
    Given user is on login page
    When user enters "<email>" and "<password>"
    Then user is successfully login
    And takes a screenshots

    Examples:
      | email                    | password     |
      | wronguser                | wrongpass123 |
      | user@seleniumacademy.com | tester       |
      | dhsnchfdus               | dfhsudfi     |