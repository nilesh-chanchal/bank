package com.management.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.management.bank.entitiy.Customer;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Long> findByNationalId(final long national_id_no);
}
