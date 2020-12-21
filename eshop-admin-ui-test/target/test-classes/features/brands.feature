@login_require
Feature: Brands

  @correct
  Scenario Outline: New brand successfully added
    Given I open Brands page
    When I click on Add Brand button
    Then New Brand page is open
    When I provide brand "<new_brand>"
    And click OK button
    Then Brands page is open
    And new brand is in the list "<new_brand>"

    Examples:
      | new_brand |
      | Test Brand |
