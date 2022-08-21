package com.management.bank.strategy;

import java.math.BigDecimal;

import com.management.bank.model.request.AccountRequest;

/**
 * Contract for different transaction types
 *
 */
public interface IStrategy {
	public BigDecimal doTransaction(final AccountRequest accountRequest);
}
