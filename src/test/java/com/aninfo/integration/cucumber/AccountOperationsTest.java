package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionType;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class AccountOperationsTest extends AccountIntegrationServiceTest {

    private Account account;
    private Transaction transaction;
    private InsufficientFundsException ife;
    private DepositNegativeSumException dnse;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^Account with a balance of (\\d+)$")
    public void account_with_a_balance_of(int balance)  {
        account = createAccount((double) balance);
    }

    @When("^Trying to withdraw (\\d+)$")
    public void trying_to_withdraw(int sum) {
        try {
            account = withdraw(account, (double) sum);
        } catch (InsufficientFundsException ife) {
            this.ife = ife;
        }
    }

    @When("^Trying to deposit (.*)$")
    public void trying_to_deposit(int sum) {
        try {
            account = deposit(account, (double) sum);
        } catch (DepositNegativeSumException dnse) {
            this.dnse = dnse;
        }
    }

    @Then("^Account balance should be (\\d+)$")
    public void account_balance_should_be(int balance) {
        assertEquals(Double.valueOf(balance), account.getBalance());
    }

    @Then("^Operation should be denied due to insufficient funds$")
    public void operation_should_be_denied_due_to_insufficient_funds() {
        assertNotNull(ife);
    }

    @Then("^Operation should be denied due to negative sum$")
    public void operation_should_be_denied_due_to_negative_sum() {
        assertNotNull(dnse);
    }

    @And("^Account balance should remain (\\d+)$")
    public void account_balance_should_remain(int balance) {
        assertEquals(Double.valueOf(balance), account.getBalance());
    }

    // Transfer Operations
    @When("^Deleting the first transaction$")
    public void deletingTheLastTransaction() {
        transaction = transactionService.getTransactionHistory(account.getCbu()).get(0);
        transactionService.deleteById(transaction.getID());
    }

    @When("^Trying to view the transaction$")
    public void tryingToViewTheTransaction() {
        transaction = transactionService.getTransactionHistory(account.getCbu()).get(0);
    }

    @Then("^Account should have a deposit transaction in it's history$")
    public void accountShouldHaveADepositTransactionInItSHistory() {
        assertNotEquals(0, transactionService.getTransactionHistory(account.getCbu()).size(),
                "The account should have a transaction in it's history.");
        assertEquals(TransactionType.DEPOSIT, transactionService.getTransactionHistory(account.getCbu()).get(0).getType(),
                "The transaction should be a deposit");
    }
    @Then("^Account should have a withdrawal transaction in it's history$")
    public void accountShouldHaveAWithdrawalTransactionInItSHistory() {
        assertNotEquals(0, transactionService.getTransactionHistory(account.getCbu()).size(),
                "The account should have a transaction in it's history.");
        assertEquals(TransactionType.WITHDRAWAL, transactionService.getTransactionHistory(account.getCbu()).get(0).getType(),
                "The transaction should be a deposit");
    }

    @Then("^The transaction should be of amount (\\d+)$")
    public void theTransactionShouldBeOfAmount(double amount) {
        assertEquals(amount, transactionService.getTransactionHistory(account.getCbu()).get(0).getAmount(),
                "The transaction should be a deposit");
    }
    @Then("^Account shouldn't have transactions in it's history$")
    public void accountShouldnTHaveTransactionsInItSHistory() {
        assertEquals(0, transactionService.getTransactionHistory(account.getCbu()).size(),
                "The account should have a transaction in it's history.");
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }

}
