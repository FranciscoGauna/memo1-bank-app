Feature: Bank transaction operations
  The deposit and extraction process should store transactions

  Scenario: Successfully created bank transaction on deposit.
    Given Account with a balance of 100
    When Trying to deposit 75
    Then Account should have a deposit transaction in it's history

  Scenario: Successfully created bank transaction on withdrawal.
    Given Account with a balance of 100
    When Trying to withdraw 75
    Then Account should have a withdrawal transaction in it's history

  Scenario: Successfully created bank transaction on withdrawal.
    Given Account with a balance of 100
    Given Trying to withdraw 75
    When Trying to view the transaction
    Then The transaction should be of amount 75

  Scenario: Successfully created and deleted bank transaction on withdrawal.
    Given Account with a balance of 100
    When Trying to withdraw 75
    When Deleting the first transaction
    Then Account shouldn't have transactions in it's history

## In these scenarios we don't generate a transaction, due to it failing
  Scenario: Cannot withdraw more money than the account balance
    Given Account with a balance of 1000
    When Trying to withdraw 1001
    Then Account shouldn't have transactions in it's history

  Scenario: Cannot deposit money when sum is negative
    Given Account with a balance of 200
    When Trying to deposit -100
    Then Account shouldn't have transactions in it's history
