package com.management.bank.model.request;

import java.math.BigDecimal;

import com.management.bank.constants.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
	private long id;
	private long customerId;
	private String accountType;
	private String status;
	private BigDecimal balance;
	private BigDecimal transactionAmount;
	private long beneficiaryId;
	private TransactionType transactionType;
}
