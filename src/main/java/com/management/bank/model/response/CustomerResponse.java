package com.management.bank.model.response;

import com.management.bank.entitiy.Customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
	private Customer customer;
	private String message;
}
