package com.management.bank.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import com.management.bank.constants.TransactionType;
import com.management.bank.entitiy.Account;
import com.management.bank.entitiy.Beneficiary;
import com.management.bank.entitiy.Customer;
import com.management.bank.entitiy.Transaction;
import com.management.bank.model.request.AccountRequest;
import com.management.bank.model.request.BeneficiaryRequest;
import com.management.bank.model.request.CustomerRequest;

/**
 * 
 * Class contains common utility methods.
 *
 */
public class CommonUtils {
	private CommonUtils() {
	}

	/**
	 * Method to get Customer Entity from CustomerRequest object.
	 * 
	 * @param customerRequest
	 * @return instance of Customer entity.
	 */
	public static Customer createCustomer(final CustomerRequest customerRequest) {
		return Customer.builder().name(customerRequest.getName()).emailId(customerRequest.getEmailId())
				.address(customerRequest.getAddress()).nationalId(customerRequest.getNationalId())
				.phoneNo(customerRequest.getPhoneNo()).password(customerRequest.getPassword()).build();
	}

	/**
	 * Method to get Customer Entity from CustomerRequest object.
	 * 
	 * @param customer
	 * @param customerRequest
	 * @return instance of Customer entity.
	 */
	public static void createCustomer(final Customer customer, final CustomerRequest customerRequest) {

		Optional.ofNullable(customerRequest.getName()).ifPresent(customer::setName);

		Optional.ofNullable(customerRequest.getEmailId()).ifPresent(customer::setEmailId);

		Optional.ofNullable(customerRequest.getAddress()).ifPresent(customer::setAddress);

		Optional.ofNullable(customerRequest.getPassword()).ifPresent(customer::setPassword);

		Optional.ofNullable(customerRequest.getPhoneNo()).ifPresent(customer::setPhoneNo);

	}

	/**
	 * Update Beneficiary Entity from BeneficiaryRequest object.
	 * 
	 * @param beneficiaryRequest
	 * @return Beneficiary
	 */
	public static Beneficiary createBeneficiary(final BeneficiaryRequest beneficiaryRequest) {
		return Beneficiary.builder().accountId(beneficiaryRequest.getAccountId()).name(beneficiaryRequest.getName())
				.accountNumber(beneficiaryRequest.getAccountNumber()).ifscCode(beneficiaryRequest.getIfsc())
				.bankName(beneficiaryRequest.getBankName()).emailId(beneficiaryRequest.getEmailId())
				.phoneNo(beneficiaryRequest.getPhoneNo()).build();
	}

	/**
	 * Method to get Beneficiary Entity from BeneficiaryRequest object.
	 * 
	 * @param beneficiaryRequest
	 * @return Beneficiary
	 */
	public static void createBeneficiary(final Beneficiary beneficiary, final BeneficiaryRequest beneficiaryRequest) {

		Optional.ofNullable(beneficiaryRequest.getName()).ifPresent(beneficiary::setName);

		Optional.ofNullable(beneficiaryRequest.getAccountNumber()).ifPresent(beneficiary::setAccountId);

		Optional.ofNullable(beneficiaryRequest.getEmailId()).ifPresent(beneficiary::setEmailId);

		Optional.ofNullable(beneficiaryRequest.getPhoneNo()).ifPresent(beneficiary::setPhoneNo);

		Optional.ofNullable(beneficiaryRequest.getBankName()).ifPresent(beneficiary::setBankName);

		Optional.ofNullable(beneficiaryRequest.getIfsc()).ifPresent(beneficiary::setIfscCode);

	}

	/**
	 * Create Account object from accoutRequest values.
	 * 
	 * @param accountRequest
	 * @return Account
	 */
	public static Account createAccount(final AccountRequest accountRequest) {
		return Account.builder().custId(accountRequest.getCustomerId()).accountType(accountRequest.getAccountType())
				.status("ACTIVE").currBalance(BigDecimal.ZERO).build();
	}

	/**
	 * Creates Credit Transaction
	 * 
	 * @param accountRequest
	 * @param account
	 * @return Transaction
	 */
	public static Transaction createCreditTransaction(final AccountRequest accountRequest, final Account account) {
		return Transaction.builder().accountId(account.getId())
				.currBalance(account.getCurrBalance().add(accountRequest.getTransactionAmount()))
				.transactionAmount(accountRequest.getTransactionAmount()).transactionType(TransactionType.CREDIT.name())
				.transactionDate(getCurrentDate()).build();

	}

	/**
	 * Creates Debit Transaction
	 * 
	 * @param accountRequest
	 * @param account
	 * @return Transaction
	 */
	public static Transaction createDebitTransaction(final AccountRequest accountRequest, final Account account) {
		return Transaction.builder().accountId(account.getId())
				.currBalance(account.getCurrBalance().subtract(accountRequest.getTransactionAmount()))
				.transactionAmount(accountRequest.getTransactionAmount()).transactionType(TransactionType.DEBIT.name())
				.transactionDate(getCurrentDate()).build();

	}

	/**
	 * Creates Transfer Transaction
	 * 
	 * @param accountRequest
	 * @param account
	 * @return Transaction
	 */
	public static Transaction createTransferTransaction(final AccountRequest accountRequest, final Account account) {
		return Transaction.builder().accountId(account.getId())
				.currBalance(account.getCurrBalance().subtract(accountRequest.getTransactionAmount()))
				.transactionAmount(accountRequest.getTransactionAmount())
				.transactionType(TransactionType.TRANSFER.name()).beneficiaryId(accountRequest.getBeneficiaryId())
				.transactionDate(getCurrentDate()).build();

	}

	public static Date getCurrentDate() {
		final long millis = System.currentTimeMillis();
		final Date date = new java.sql.Date(millis);
		return date;
	}
}
