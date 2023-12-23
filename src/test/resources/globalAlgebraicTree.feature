Feature: as a user, i want to see the algebraic tree from my query

  Scenario: Getting Algebraic tree
    Given a query
    When using the generator of algebraic tree program
    Then getting tree structure from my query
