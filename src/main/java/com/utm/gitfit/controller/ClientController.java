package com.utm.gitfit.controller;

import com.utm.gitfit.dto.ClientDto;
import com.utm.gitfit.model.response.ClientResponse;
import com.utm.gitfit.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientDto clientDto) {
        ClientResponse savedClient = clientService.save(clientDto);

        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .build(savedClient.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(savedClient);
    }

    @PutMapping
    public ResponseEntity<ClientResponse> update(@Valid @RequestBody ClientDto clientDto) {
        ClientResponse updatedClient = clientService.update(clientDto);

        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .build(updatedClient.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .location(uri)
                .body(updatedClient);
    }
}
