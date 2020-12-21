@logintest
Feature: Login

  Background:
    Given I open a web browser
    And I navigate to the login page

  @correct
  Scenario Outline: Successful login to the page and logout after
    Given I provide username as "<username>" and password as "<password>"
    When I click on login button
    Then page title must be "<title>"
    And name should be "<name>"
    When Open dropdown menu
    And click logout button
    Then user logged out

    Examples:
      | username | password | name | title |
      | admin | admin | admin | eshop management |

  @fail
  Scenario Outline: Fail when login to the page
    Given I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then page is the same and error message is "<error>"

    Examples:
      | username | password | error |
      | test | test | Invalid username or password |