package com.utm.gitfit;

import com.utm.gitfit.model.entities.Role;
import com.utm.gitfit.model.enums.ERole;
import com.utm.gitfit.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        Role clientRole = new Role();
        clientRole.setName(ERole.CLIENT);

        Role adminRole = new Role();
        adminRole.setName(ERole.ADMIN);

        Role coachRole = new Role();
        coachRole.setName(ERole.COACH);

        roleRepository.save(clientRole);
        roleRepository.save(coachRole);
        roleRepository.save(adminRole);
    }
}
