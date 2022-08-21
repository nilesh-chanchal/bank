package com.management.bank.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.bank.entitiy.Account;
import com.management.bank.entitiy.Beneficiary;
import com.management.bank.entitiy.Transaction;
import com.management.bank.exception.AccountNotFoundException;
import com.management.bank.model.request.AccountRequest;
import com.management.bank.model.response.AccountResponse;
import com.management.bank.repository.AccountRepository;
import com.management.bank.repository.BeneficiaryRepository;
import com.management.bank.repository.TransactionRespository;
import com.management.bank.services.AccountService;
import com.management.bank.strategy.IStrategy;
import com.management.bank.strategy.StrategyFactory;
import com.management.bank.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRespository transactionRepository;

	@Autowired
	private StrategyFactory strategyFactory;

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public AccountResponse createAccount(AccountRequest accountRequest) {
		log.info("Account service implementation for " + accountRequest.toString());

		if (this.accountRepository.findByCustId(accountRequest.getCustomerId()).isPresent()) {
			log.info("Customer account already exist with customer id - " + accountRequest.getCustomerId());
			return AccountResponse.builder().message("Customer already exist!").build();
		}

		final Account account = CommonUtils.createAccount(accountRequest);
		this.accountRepository.save(account);
		log.info("Account created successfully - " + account.toString());
		return AccountResponse.builder().account(account).message("Account created successfully!").build();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public BigDecimal getBalance(final long accountId) {
		Optional<Account> accountOpt = this.accountRepository.findById(accountId);
		if (!accountOpt.isPresent()) {
			throw new AccountNotFoundException("Account id doesn't exit.");
		}

		return accountOpt.get().getCurrBalance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<Transaction> getTransactions(final long accountId) {
		Optional<Account> accountOpt = this.accountRepository.findById(accountId);
		if (!accountOpt.isPresent()) {
			throw new AccountNotFoundException("Account id doesn't exit.");
		}

		Optional<List<Transaction>> transactions = this.transactionRepository.findAllByAccountId(accountId);

		return transactions.get();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Transactional
	@Override
	public BigDecimal doTransaction(final AccountRequest accountRequest) {
		final IStrategy transactionStrategy = this.strategyFactory.getStrategy(accountRequest.getTransactionType());
		final BigDecimal balance = transactionStrategy.doTransaction(accountRequest);
		return balance;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public AccountResponse getSummary(final long accountId) {
		Optional<Account> accountOpt = this.accountRepository.findById(accountId);
		if (!accountOpt.isPresent()) {
			throw new AccountNotFoundException("Account id doesn't exit.");
		}
		Optional<List<Transaction>> transactionsOpt = this.transactionRepository.findAllByAccountId(accountId);

		Optional<List<Beneficiary>> beneficiariesOpt = this.beneficiaryRepository.findAllByAccountId(accountId);

		return AccountResponse.builder().account(accountOpt.get()).transactions(transactionsOpt.get())
				.beneficiaries(beneficiariesOpt.get()).build();
	}
}
