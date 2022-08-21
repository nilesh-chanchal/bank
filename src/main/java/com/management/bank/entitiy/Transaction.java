package com.management.bank.entitiy;

import static com.management.bank.constants.ColumnConstants.ACCOUNT_ID;
import static com.management.bank.constants.ColumnConstants.BENEFICIARY_ID;
import static com.management.bank.constants.ColumnConstants.ID;
import static com.management.bank.constants.ColumnConstants.TRANSACTION_AMOUNT;
import static com.management.bank.constants.ColumnConstants.TRANSACTION_CURRENT_BALANCE;
import static com.management.bank.constants.ColumnConstants.TRANSACTION_DATE;
import static com.management.bank.constants.ColumnConstants.TRANSACTION_TYPE;
import static com.management.bank.constants.TableConstants.ALLOCATION_SIZE;
import static com.management.bank.constants.TableConstants.TRANSACTION_ID_SEQ;
import static com.management.bank.constants.TableConstants.TRANSACTION_TABLE;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = TRANSACTION_TABLE)
public class Transaction {
	@SuppressWarnings("unused")
	private static final long serialVerionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TRANSACTION_ID_SEQ)
	@SequenceGenerator(name = TRANSACTION_ID_SEQ, sequenceName = TRANSACTION_ID_SEQ, allocationSize = ALLOCATION_SIZE)
	@Column(name = ID)
	private long id;

	@Column(name = ACCOUNT_ID)
	private long accountId;

	@Column(name = TRANSACTION_TYPE)
	private String transactionType;

	@Column(name = TRANSACTION_AMOUNT)
	private BigDecimal transactionAmount;

	@Column(name = TRANSACTION_CURRENT_BALANCE)
	private BigDecimal currBalance;

	@Column(name = TRANSACTION_DATE)
	private Date transactionDate;

	@Column(name = BENEFICIARY_ID)
	private long beneficiaryId;
}
