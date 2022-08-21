package com.management.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.management.bank.entitiy.Transaction;

@Repository
@EnableJpaRepositories
public interface TransactionRespository extends JpaRepository<Transaction, Long> {

	Optional<List<Transaction>> findAllByAccountId(final long accountId);
}
