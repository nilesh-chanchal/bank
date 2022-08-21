package com.management.bank.services;

import java.util.List;

import com.management.bank.entitiy.Customer;
import com.management.bank.model.request.CustomerRequest;
import com.management.bank.model.response.CustomerResponse;

public interface CustomerService {

	/**
	 * Insert customer in the database if already doesn't exist.
	 * 
	 * @param customerRequest
	 * @return CustomerResponse
	 */
	public CustomerResponse addCustomer(final CustomerRequest customerRequest);

	/**
	 * Fetches all the customers from database.
	 * 
	 * @return List of customers
	 */
	public List<Customer> getCustomers();

	/**
	 * Update customer
	 * 
	 * @param customerRequest
	 * @return CustomerResponse
	 */

	public CustomerResponse updateCustomer(final CustomerRequest customerRequest);

}
