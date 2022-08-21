package com.management.bank.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.bank.entitiy.Customer;
import com.management.bank.model.request.CustomerRequest;
import com.management.bank.model.response.CustomerResponse;
import com.management.bank.repository.CustomerRepository;
import com.management.bank.services.CustomerService;
import com.management.bank.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public CustomerResponse addCustomer(CustomerRequest customerRequest) {
		log.info("Customer service implementation for " + customerRequest.toString());

		if (this.customerRepository.findByNationalId(customerRequest.getNationalId()).isPresent()) {
			log.info("Customer already exist with National id - " + customerRequest.getNationalId());
			return CustomerResponse.builder().message("Customer already exist!").build();
		}

		final Customer customer = CommonUtils.createCustomer(customerRequest);
		this.customerRepository.save(customer);
		log.info("Customer saved successfully - " + customer.toString());
		return CustomerResponse.builder().customer(customer).message("Customer added successfully!").build();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Transactional
	@Override
	public CustomerResponse updateCustomer(final CustomerRequest customerRequest) {
		log.info("Customer service implementation for " + customerRequest.toString());
		final Optional<Customer> customerOpt = this.customerRepository.findById(customerRequest.getId());
		if (!customerOpt.isPresent()) {
			log.info("Customer doesn't exist with customer id - " + customerRequest.getId());
			return CustomerResponse.builder().message("Customer doesn't exit!").build();
		}

		final Customer customer = customerOpt.get();

		CommonUtils.createCustomer(customer, customerRequest);
		this.customerRepository.save(customer);
		log.info("Customer updated successfully - " + customer.toString());
		return CustomerResponse.builder().customer(customer).message("Customer updated successfully!").build();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<Customer> getCustomers() {
		return this.customerRepository.findAll();
	}

}
