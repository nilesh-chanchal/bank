package com.management.bank.entitiy;

import static com.management.bank.constants.ColumnConstants.PROFILE_ADDRESS;
import static com.management.bank.constants.ColumnConstants.PROFILE_EMAIL_ID;
import static com.management.bank.constants.ColumnConstants.PROFILE_NAME;
import static com.management.bank.constants.ColumnConstants.PROFILE_NATIONAL_ID;
import static com.management.bank.constants.ColumnConstants.PROFILE_PASSWORD;
import static com.management.bank.constants.ColumnConstants.PROFILE_PHONE_NO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class ProfileBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final long serialVerionUID = 1L;

	@Column(name = PROFILE_NAME)
	private String name;

	@Column(name = PROFILE_EMAIL_ID)
	private String emailId;

	@Column(name = PROFILE_ADDRESS)
	private String address;

	@Column(name = PROFILE_PHONE_NO)
	private long phoneNo;

	@Column(name = PROFILE_NATIONAL_ID)
	private long nationalId;

	@Column(name = PROFILE_PASSWORD)
	private String password;
}
