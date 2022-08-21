package com.management.bank.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.bank.entitiy.Beneficiary;
import com.management.bank.model.request.BeneficiaryRequest;
import com.management.bank.model.response.BeneficiaryResponse;
import com.management.bank.services.BeneficiaryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bank-management/api/v1/beneficiary")
public class BeneficiaryController {

	@Autowired
	private BeneficiaryService beneficiaryService;

	/**
	 * Add Beneficiary to database.
	 * 
	 * @param beneficiaryRequest
	 * @return ResponseEntity
	 */
	@PostMapping("/add")
	public final ResponseEntity<BeneficiaryResponse> addBeneficiary(
			final @RequestBody @Valid BeneficiaryRequest beneficiaryRequest) {
		log.info("Beneficiary Controller " + beneficiaryRequest);
		final BeneficiaryResponse beneficiaryResponse = this.beneficiaryService.addBeneficiary(beneficiaryRequest);
		return ResponseEntity.status(HttpStatus.OK).body(beneficiaryResponse);
	}

	/**
	 * update Beneficiary.
	 * 
	 * @param beneficiaryRequest
	 * @return ResponseEntity
	 */
	@PostMapping("/update")
	public final ResponseEntity<BeneficiaryResponse> updateBeneficiary(
			final @RequestBody @Valid BeneficiaryRequest beneficiaryRequest) {
		log.info("Beneficiary Controller " + beneficiaryRequest);
		final BeneficiaryResponse beneficiaryResponse = this.beneficiaryService.updateBeneficiary(beneficiaryRequest);
		return ResponseEntity.status(HttpStatus.OK).body(beneficiaryResponse);
	}

	/**
	 * Fetches all the Beneficiary for a client from database.
	 * 
	 * @return List of Beneficiary
	 */
	@GetMapping("/all")
	public final ResponseEntity<List<Beneficiary>> getBeneficiaries(
			@RequestParam(value = "accountId") @NotNull final long accountId) {
		log.info("Fetching  Beneficiaries list.");
		final List<Beneficiary> beneficiaries = this.beneficiaryService.getBeneficiaries(accountId);
		return ResponseEntity.status(HttpStatus.OK).body(beneficiaries);
	}
}
