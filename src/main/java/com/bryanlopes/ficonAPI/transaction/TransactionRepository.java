package com.bryanlopes.ficonAPI.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM transactions t WHERE lower(t.name) = lower(:transactionName) AND t.active = true COLLATE utf8mb4_general_ci", nativeQuery = true)
    Page<Transaction> findByNameIgnoreCaseAndActiveIsTrue(@Param("transactionName") String transactionName, Pageable pageable);

    Transaction findByIdAndActiveIsTrue(Long id);
}
