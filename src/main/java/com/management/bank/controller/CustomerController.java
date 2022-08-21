package com.management.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.bank.entitiy.Customer;
import com.management.bank.model.request.CustomerRequest;
import com.management.bank.model.response.CustomerResponse;
import com.management.bank.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bank-management/api/v1/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * Add customer to database.
	 * 
	 * @param customerRequest
	 * @return ResponseEntity
	 */
	@PostMapping("/add")
	public final ResponseEntity<CustomerResponse> addCustomer(
			final @RequestBody @Valid CustomerRequest customerRequest) {
		log.info("Customer Controller " + customerRequest);
		final CustomerResponse customerResponse = this.customerService.addCustomer(customerRequest);
		return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
	}
	
	/**
	 * update customer.
	 * 
	 * @param customerRequest
	 * @return ResponseEntity
	 */
	@PostMapping("/update")
	public final ResponseEntity<CustomerResponse> updateCustomer(
			final @RequestBody @Valid CustomerRequest customerRequest) {
		log.info("Customer Controller " + customerRequest);
		final CustomerResponse customerResponse = this.customerService.updateCustomer(customerRequest);
		return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
	}

	/**
	 * Fetches all the customers from database.
	 * 
	 * @return List of Customers
	 */
	@GetMapping("/all")
	public final ResponseEntity<List<Customer>> getCustomers() {
		log.info("Fetching all customers list.");
		final List<Customer> customers = this.customerService.getCustomers();
		return ResponseEntity.status(HttpStatus.OK).body(customers);
	}
}
