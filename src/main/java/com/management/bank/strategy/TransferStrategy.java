package com.management.bank.strategy;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.bank.entitiy.Account;
import com.management.bank.entitiy.Transaction;
import com.management.bank.exception.AccountNotFoundException;
import com.management.bank.exception.InsufficientBalanceException;
import com.management.bank.model.request.AccountRequest;
import com.management.bank.repository.AccountRepository;
import com.management.bank.repository.TransactionRespository;
import com.management.bank.util.CommonUtils;

@Component
public class TransferStrategy implements IStrategy {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRespository transactionRepository;

	@Override
	public BigDecimal doTransaction(AccountRequest accountRequest) {
		final Optional<Account> accountOpt = this.accountRepository.findById(accountRequest.getId());

		if (!accountOpt.isPresent()) {
			throw new AccountNotFoundException("Account id doesn't exit.");
		}

		final Account account = accountOpt.get();

		if (account.getCurrBalance().compareTo(accountRequest.getTransactionAmount()) == -1) {
			throw new InsufficientBalanceException("Insufficient balance in the account");
		}

		insertTransaction(accountRequest, account);

		updateAccount(accountRequest, account);

		return account.getCurrBalance();
	}

	private void updateAccount(final AccountRequest accountRequest, final Account account) {

		account.setCurrBalance(account.getCurrBalance().subtract(accountRequest.getTransactionAmount()));

		this.accountRepository.save(account);

	}

	private void insertTransaction(final AccountRequest accountRequest, final Account account) {
		final Transaction debitTransaction = CommonUtils.createTransferTransaction(accountRequest, account);
		this.transactionRepository.save(debitTransaction);
	}
}
