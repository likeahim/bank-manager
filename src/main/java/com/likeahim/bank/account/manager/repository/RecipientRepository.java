package com.likeahim.bank.account.manager.repository;

import com.likeahim.bank.account.manager.domain.transaction.Recipient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RecipientRepository extends JpaRepository<Recipient, Long> {
}
