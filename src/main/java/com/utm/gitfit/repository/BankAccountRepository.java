package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BillingDetails, Long> {
}
