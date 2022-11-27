package com.utm.gitfit.service;

import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.mapper.BillingDetailsMapper;
import com.utm.gitfit.model.dto.BillingDetailsDto;
import com.utm.gitfit.model.entities.BillingDetails;
import com.utm.gitfit.model.entities.User;
import com.utm.gitfit.repository.BillingDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingDetailsService {

    private final BillingDetailsRepository billingDetailsRepository;
    private final BillingDetailsMapper billingDetailsMapper;
    private final UserService userService;

    private BillingDetails getById(Long id) {
        return billingDetailsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("BankAccount with id: " + id + " not found."));
    }

    public BillingDetailsDto save(BillingDetailsDto billingDetailsDto) {
        BillingDetails billingDetails = billingDetailsMapper.map(billingDetailsDto);
        User user = userService.getCurrentUser();
        billingDetails.setUser(user);
        user.setBillingDetails(billingDetails);
        User savedUser = userService.save(user);
        return billingDetailsMapper.map(savedUser.getBillingDetails());
    }
}
