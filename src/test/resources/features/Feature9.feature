Feature: very first cucumber test9


  Scenario: first scenario9
    Given I report next in log: 'Hello from 1st scenario'

  Scenario: second scenario9
    Given I report next in log: 'Hello from 2nd scenario'

  Scenario: third scenario9
    Given I report next in log: 'Hello from 3rd scenario'

  Scenario Outline: fourth scenario9
    Given I report next in log: '<param>'

    Examples:
      | param     |
      | 1st param |
      | 2nd param |
      | 3rd param |
      | 4th param |
      | 5y param  |