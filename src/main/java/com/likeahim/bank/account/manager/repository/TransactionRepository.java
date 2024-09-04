package com.likeahim.bank.account.manager.repository;

import com.likeahim.bank.account.manager.domain.transaction.AccountTransaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<AccountTransaction, Long> {
}
