package com.management.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.management.bank.entitiy.Account;

@Repository
@EnableJpaRepositories
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Long> findByCustId(final long customerId);	
	

}
