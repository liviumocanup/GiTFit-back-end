package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Long> {

    Optional<BillingDetails> findByUser_Id(Long userId);
}
