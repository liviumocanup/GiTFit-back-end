package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
