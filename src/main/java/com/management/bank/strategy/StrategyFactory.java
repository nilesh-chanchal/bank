package com.management.bank.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.bank.constants.TransactionType;

@Component
public class StrategyFactory {
	@Autowired
	private IStrategy creditStrategy;

	@Autowired
	private IStrategy debitStrategy;

	@Autowired
	private IStrategy transferStrategy;

	public IStrategy getStrategy(final TransactionType transactionType) {
		switch (transactionType) {
		case CREDIT:
			return this.creditStrategy;
		case DEBIT:
			return this.debitStrategy;
		case TRANSFER:
			return this.transferStrategy;
		default:
			throw new IllegalArgumentException("Invalid AssetClass");
		}
	}
}
