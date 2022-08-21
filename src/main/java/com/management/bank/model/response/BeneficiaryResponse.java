package com.management.bank.model.response;

import com.management.bank.entitiy.Beneficiary;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeneficiaryResponse {
	private Beneficiary beneficiary;
	private String message;
}
