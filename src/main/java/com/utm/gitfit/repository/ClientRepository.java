package com.utm.gitfit.repository;

import com.utm.gitfit.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByIdAndNameAndLastName(Long id, String name, String lastName);
}
