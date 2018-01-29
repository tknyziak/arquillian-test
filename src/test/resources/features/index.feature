Feature: Index page

  Scenario: Entering the index page

    When I hit the welcome page
    Then I should see a <h1> element with "Hello, world!" inside

