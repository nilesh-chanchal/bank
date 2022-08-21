package com.management.bank.entitiy;

import static com.management.bank.constants.ColumnConstants.BENEFICIARY_ACCOUNT_NO;
import static com.management.bank.constants.ColumnConstants.BENEFICIARY_BANK_NAME;
import static com.management.bank.constants.ColumnConstants.BENEFICIARY_EMAIL_ID;
import static com.management.bank.constants.ColumnConstants.BENEFICIARY_IFSC_CODE;
import static com.management.bank.constants.ColumnConstants.BENEFICIARY_NAME;
import static com.management.bank.constants.ColumnConstants.BENEFICIARY_PHONE_NO;
import static com.management.bank.constants.ColumnConstants.ACCOUNT_ID;
import static com.management.bank.constants.ColumnConstants.ID;
import static com.management.bank.constants.TableConstants.ALLOCATION_SIZE;
import static com.management.bank.constants.TableConstants.BENEFICIARY_ID_SEQ;
import static com.management.bank.constants.TableConstants.BENEFICIARY_TABLE;

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
@Table(name = BENEFICIARY_TABLE)
public class Beneficiary {

	@SuppressWarnings("unused")
	private static final long serialVerionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BENEFICIARY_ID_SEQ)
	@SequenceGenerator(name = BENEFICIARY_ID_SEQ, sequenceName = BENEFICIARY_ID_SEQ, allocationSize = ALLOCATION_SIZE)
	@Column(name = ID)
	private long id;

	@Column(name = ACCOUNT_ID)
	private long accountId;

	@Column(name = BENEFICIARY_ACCOUNT_NO)
	private long accountNumber;

	@Column(name = BENEFICIARY_NAME)
	private String name;

	@Column(name = BENEFICIARY_EMAIL_ID)
	private String emailId;

	@Column(name = BENEFICIARY_PHONE_NO)
	private long phoneNo;

	@Column(name = BENEFICIARY_IFSC_CODE)
	private String ifscCode;

	@Column(name = BENEFICIARY_BANK_NAME)
	private String bankName;

}
