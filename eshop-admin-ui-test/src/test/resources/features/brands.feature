@login_require
Feature: Brands

  Background:
    Given I open Brands page
    When I click on Add Brand button
    Then New Brand page "brand | eshop management" is open
    When I provide brand "test brand"
    And click OK button and return to Brands page

  Scenario Outline: New brand successfully added
    Then new brand is on the list "<new_brand>"

    Examples:
      | new_brand |
      | test brand |

  Scenario Outline: Old brand successfully updated
    When I find the brand "<brand>" and I click the Edit button
    Then New Brand page "brand | eshop management" is open
    And I see the current brand "<brand>"
    And I clear the Brand field
    When I provide brand "<new_brand>"
    And click OK button and return to Brands page
    Then new brand is on the list "<new_brand>"

    Examples:
      | brand | new_brand |
      | test brand | updated brand |