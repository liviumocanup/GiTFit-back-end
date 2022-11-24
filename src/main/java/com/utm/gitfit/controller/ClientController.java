package com.utm.gitfit.controller;

import com.utm.gitfit.model.response.ClientResponse;
import com.utm.gitfit.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientResponse> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientResponse findById(@PathVariable long id) {
        return clientService.findById(id);
    }
}
