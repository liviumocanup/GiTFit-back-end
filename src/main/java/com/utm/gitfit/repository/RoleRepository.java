package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.Role;
import com.utm.gitfit.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
