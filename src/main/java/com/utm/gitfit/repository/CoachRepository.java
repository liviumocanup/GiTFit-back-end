package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findByIdAndNameAndLastName(Long id, String name, String lastName);
}
