Feature: very first cucumber test6


  Scenario: first scenario6
    Given I report next in log: 'Hello from 1st scenario'

  Scenario: second scenario6
    Given I report next in log: 'Hello from 2nd scenario'

  Scenario: third scenario6
    Given I report next in log: 'Hello from 3rd scenario'

  Scenario Outline: fourth scenario6
    Given I report next in log: '<param>'

    Examples:
      | param     |
      | 1st param |
      | 2nd param |
      | 3rd param |
      | 4th param |
      | 5y param  |