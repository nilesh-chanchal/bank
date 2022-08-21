package com.management.bank.services.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.management.bank.BaseTest;
import com.management.bank.entitiy.Account;
import com.management.bank.exception.AccountNotFoundException;
import com.management.bank.model.request.AccountRequest;
import com.management.bank.model.response.AccountResponse;
import com.management.bank.repository.AccountRepository;
import com.management.bank.repository.BeneficiaryRepository;
import com.management.bank.repository.TransactionRespository;
import com.management.bank.strategy.StrategyFactory;

public class AccountServiceTest extends BaseTest {

	@InjectMocks
	private AccountServiceImpl accountService;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private TransactionRespository transactionRepository;

	@Mock
	private StrategyFactory strategyFactory;

	@Mock
	private BeneficiaryRepository beneficiaryRepository;

	@Test
	public void testCreateAccountValid() {
		final AccountRequest accountRequest = AccountRequest.builder().customerId(2L).accountType("SAVING").build();

		when(this.accountRepository.findByCustId(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		when(this.accountRepository.save(ArgumentMatchers.any(Account.class))).thenReturn(null);

		final AccountResponse actualResponse = this.accountService.createAccount(accountRequest);

		assertNotNull(actualResponse);
		assertEquals("Account created successfully!", actualResponse.getMessage());
		assertEquals(2L, actualResponse.getAccount().getCustId());
		assertEquals("SAVING", actualResponse.getAccount().getAccountType());
	}

	@Test(expected = AccountNotFoundException.class)
	public void testGetBalanceWithInvalidAccountId() {
		when(this.accountRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		this.accountService.getBalance(2L);
	}
}
