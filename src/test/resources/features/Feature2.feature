Feature: very first cucumber test2


  Scenario: first scenario2
    Given I report next in log: 'Hello from 1st scenario'

  Scenario: second scenario2
    Given I report next in log: 'Hello from 2nd scenario'

  Scenario: third scenario2
    Given I report next in log: 'Hello from 3rd scenario'

  Scenario Outline: fourth scenario2
    Given I report next in log: '<param>'

    Examples:
      | param     |
      | 1st param |
      | 2nd param |
      | 3rd param |
      | 4th param |
      | 5y param  |