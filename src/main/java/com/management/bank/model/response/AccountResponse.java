package com.management.bank.model.response;

import java.util.List;

import com.management.bank.entitiy.Account;
import com.management.bank.entitiy.Beneficiary;
import com.management.bank.entitiy.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
	private Account account;
	private List<Transaction> transactions;
	private List<Beneficiary> beneficiaries;
	private String message;
}
