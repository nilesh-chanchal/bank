package com.management.bank.services;

import java.util.List;

import com.management.bank.entitiy.Beneficiary;
import com.management.bank.model.request.BeneficiaryRequest;
import com.management.bank.model.response.BeneficiaryResponse;

public interface BeneficiaryService {
	/**
	 * Insert Beneficiary in the database if already doesn't exist.
	 * 
	 * @param beneficiaryRequest
	 * @return BeneficiaryResponse
	 */
	public BeneficiaryResponse addBeneficiary(final BeneficiaryRequest beneficiaryRequest);

	/**
	 * Fetches all the Beneficiaries for an account from database.
	 * 
	 * @param long accountId
	 * @return List of Beneficiaries
	 */
	public List<Beneficiary> getBeneficiaries(final long accountId);

	/**
	 * Update Beneficiary
	 * 
	 * @param customerRequest
	 * @return CustomerResponse
	 */

	public BeneficiaryResponse updateBeneficiary(final BeneficiaryRequest beneficiaryRequest);
}
