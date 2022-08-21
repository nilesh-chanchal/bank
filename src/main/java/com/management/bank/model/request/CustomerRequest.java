package com.management.bank.model.request;

import lombok.Data;

@Data
public class CustomerRequest {

	private long id;

	private String name;

	private String emailId;

	private String address;

	private long phoneNo;

	private long nationalId;

	private String password;
}
