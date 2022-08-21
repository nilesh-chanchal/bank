package com.management.bank.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.bank.entitiy.Transaction;
import com.management.bank.model.request.AccountRequest;
import com.management.bank.model.response.AccountResponse;
import com.management.bank.services.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bank-management/api/v1/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	/**
	 * create account for the given customer id.
	 * 
	 * @param customerRequest
	 * @return ResponseEntity
	 */
	@PostMapping("/create")
	public final ResponseEntity<AccountResponse> createAccount(
			final @RequestBody @Valid AccountRequest accountRequest) {
		log.info("Account Controller " + accountRequest);
		final AccountResponse accountResponse = this.accountService.createAccount(accountRequest);
		return ResponseEntity.status(HttpStatus.OK).body(accountResponse);
	}

	/**
	 * Fetches current balance for the account.
	 * 
	 * @param accountId
	 * @return
	 */
	@GetMapping("/balance")
	public final ResponseEntity<BigDecimal> getBalance(
			@RequestParam(value = "accountId") @NotNull final long accountId) {
		final BigDecimal balance = this.accountService.getBalance(accountId);
		return ResponseEntity.status(HttpStatus.OK).body(balance);

	}

	/**
	 * Fetches transactions for the account.
	 * 
	 * @param accountId
	 * @return
	 */
	@GetMapping("/transactions")
	public final ResponseEntity<List<Transaction>> getTransactions(
			@RequestParam(value = "accountId") @NotNull final long accountId) {
		final List<Transaction> transactions = this.accountService.getTransactions(accountId);
		return ResponseEntity.status(HttpStatus.OK).body(transactions);

	}

	/**
	 * Make credit transaction for the account.
	 * 
	 * @param accountId
	 * @return ResponseEntity
	 */
	@PostMapping("/transaction")
	public final ResponseEntity<BigDecimal> doTransaction(final @RequestBody @Valid AccountRequest accountRequest) {
		final BigDecimal balance = this.accountService.doTransaction(accountRequest);
		return ResponseEntity.status(HttpStatus.OK).body(balance);

	}

	@GetMapping("/summary")
	public final ResponseEntity<AccountResponse> getSummary(
			@RequestParam(value = "accountId") @NotNull final long accountId) {
		final AccountResponse accountResponse = this.accountService.getSummary(accountId);
		return ResponseEntity.status(HttpStatus.OK).body(accountResponse);
	}

}
