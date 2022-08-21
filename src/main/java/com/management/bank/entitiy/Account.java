package com.management.bank.entitiy;

import static com.management.bank.constants.ColumnConstants.ACCOUNT_CURRENT_BALANCE;
import static com.management.bank.constants.ColumnConstants.ACCOUNT_STATUS;
import static com.management.bank.constants.ColumnConstants.ACCOUNT_TYPE;
import static com.management.bank.constants.ColumnConstants.CUSTOMER_CUST_ID;
import static com.management.bank.constants.ColumnConstants.ID;
import static com.management.bank.constants.TableConstants.ACCOUNT_ID_SEQ;
import static com.management.bank.constants.TableConstants.ACCOUNT_TABLE;
import static com.management.bank.constants.TableConstants.ALLOCATION_SIZE;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ACCOUNT_TABLE)
public class Account {

	@SuppressWarnings("unused")
	private static final long serialVerionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ACCOUNT_ID_SEQ)
	@SequenceGenerator(name = ACCOUNT_ID_SEQ, sequenceName = ACCOUNT_ID_SEQ, allocationSize = ALLOCATION_SIZE)
	@Column(name = ID)
	private long id;

	@Column(name = ACCOUNT_TYPE)
	private String accountType;

	@Column(name = CUSTOMER_CUST_ID)
	private long custId;

	@Column(name = ACCOUNT_STATUS)
	private String status;

	@Column(name = ACCOUNT_CURRENT_BALANCE)
	private BigDecimal currBalance;

}
