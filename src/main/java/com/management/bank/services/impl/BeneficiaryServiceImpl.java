package com.management.bank.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.bank.entitiy.Beneficiary;
import com.management.bank.exception.AccountNotFoundException;
import com.management.bank.model.request.BeneficiaryRequest;
import com.management.bank.model.response.BeneficiaryResponse;
import com.management.bank.repository.AccountRepository;
import com.management.bank.repository.BeneficiaryRepository;
import com.management.bank.services.BeneficiaryService;
import com.management.bank.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;

	@Autowired
	private AccountRepository accountRepository;

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public BeneficiaryResponse addBeneficiary(final BeneficiaryRequest beneficiaryRequest) {

		if (!this.accountRepository.findById(beneficiaryRequest.getAccountId()).isPresent()) {
			log.info("Customer doesn't exist with account id - " + beneficiaryRequest.getAccountId());
			throw new AccountNotFoundException("Account doesn't exit");
		}

		if (this.beneficiaryRepository.findBeneficiaryByAccountDetails(beneficiaryRequest.getAccountId(),
				beneficiaryRequest.getAccountNumber(), beneficiaryRequest.getIfsc(), beneficiaryRequest.getBankName())
				.isPresent()) {
			log.info("Beneficiary already exist for account id - " + beneficiaryRequest.getAccountId());
			return BeneficiaryResponse.builder().message("Beneficiary with given account details already exist!")
					.build();
		}
		final Beneficiary beneficiary = CommonUtils.createBeneficiary(beneficiaryRequest);
		this.beneficiaryRepository.save(beneficiary);

		return BeneficiaryResponse.builder().beneficiary(beneficiary).message("Beneficiary added successfully!")
				.build();

	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<Beneficiary> getBeneficiaries(final long accountId) {
		return this.beneficiaryRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public BeneficiaryResponse updateBeneficiary(final BeneficiaryRequest beneficiaryRequest) {
		log.info("Beneficiary service implementation for " + beneficiaryRequest.toString());
		final Optional<Beneficiary> beneficiaryOpt = this.beneficiaryRepository.findById(beneficiaryRequest.getId());
		if (!beneficiaryOpt.isPresent()) {
			log.info("Customer doesn't exist with customer id - " + beneficiaryRequest.getId());
			return BeneficiaryResponse.builder().message("Beneficiary doesn't exit!").build();
		}

		final Beneficiary beneficiary = beneficiaryOpt.get();

		CommonUtils.createBeneficiary(beneficiary, beneficiaryRequest);
		this.beneficiaryRepository.save(beneficiary);
		log.info("Beneficiary updated successfully - " + beneficiary.toString());
		return BeneficiaryResponse.builder().beneficiary(beneficiary).message("Beneficiary updated successfully!")
				.build();
	}

}
