package com.management.bank.services;

import java.math.BigDecimal;
import java.util.List;

import com.management.bank.entitiy.Transaction;
import com.management.bank.model.request.AccountRequest;
import com.management.bank.model.response.AccountResponse;

public interface AccountService {

	/**
	 * create account for the given account request.
	 * 
	 * @param accountRequest
	 * @return AccountResponse
	 */
	public AccountResponse createAccount(final AccountRequest accountRequest);

	/**
	 * Fetches current balance for the account associated with given accountId;
	 * 
	 * @param accountId
	 * @return BigDecimal current balance
	 */
	public BigDecimal getBalance(final long accountId);

	/**
	 * Fetches transactions for the account associated with given accountId;
	 * 
	 * @param accountId
	 * @return List of transactions.
	 */
	public List<Transaction> getTransactions(final long accountId);

	/**
	 * Make transaction to account.
	 * 
	 * @param accountRequest
	 * @return BigDecimal balance.
	 */
	public BigDecimal doTransaction(final AccountRequest accountRequest);

	/**
	 * Provide the summary of the account.
	 * 
	 * @param accountId
	 * @return AccountResponse
	 */
	public AccountResponse getSummary(final long accountId);
}
