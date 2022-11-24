package com.utm.gitfit.controller;

import com.utm.gitfit.model.dto.BillingDetailsDto;
import com.utm.gitfit.service.BillingDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/billing")
public class BillingDetailsController {

    private final BillingDetailsService billingDetailsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBillingDetailsForCurrentUser(@RequestBody BillingDetailsDto billingDetailsDto){
        billingDetailsService.save(billingDetailsDto);
    }
}
