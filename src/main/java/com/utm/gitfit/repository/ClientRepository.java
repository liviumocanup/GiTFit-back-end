package com.utm.gitfit.repository;

import com.utm.gitfit.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
