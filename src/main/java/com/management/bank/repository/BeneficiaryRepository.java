package com.management.bank.repository;

import static com.management.bank.constants.QueryConstants.ACCOUNT_ID_KEY;
import static com.management.bank.constants.QueryConstants.ACCOUNT_NUMBER_KEY;
import static com.management.bank.constants.QueryConstants.BANK_NAME_KEY;
import static com.management.bank.constants.QueryConstants.IFSC_KEY;
import static com.management.bank.constants.QueryConstants.FIND_BENEFICIARY_BY_ACCOUNT_DETAILS;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.management.bank.entitiy.Beneficiary;

@Repository
@EnableJpaRepositories
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

	@Query(value = FIND_BENEFICIARY_BY_ACCOUNT_DETAILS, nativeQuery = true)
	Optional<Long> findBeneficiaryByAccountDetails(@Param(ACCOUNT_ID_KEY) final long customerId,
			@Param(ACCOUNT_NUMBER_KEY) final long accountNubmer, @Param(IFSC_KEY) final String ifsc,
			@Param(BANK_NAME_KEY) final String bankName);

	Optional<List<Beneficiary>> findAllByAccountId(final long accountId);

}
