package com.management.bank.model.request;

import lombok.Data;

@Data
public class BeneficiaryRequest {
	private long id;
	private long accountId;
	private String name;
	private long accountNumber;
	private String emailId;
	private long phoneNo;
	private String ifsc;
	private String bankName;
}
