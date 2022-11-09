package com.utm.gitfit.controller;

import com.utm.gitfit.dto.ClientDto;
import com.utm.gitfit.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @GetMapping("{id}")
    public ClientDto findById(@PathVariable long id) {
        return clientService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ClientDto clientDto) {
        ClientDto savedClient = clientService.save(clientDto);

        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .build(savedClient.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(savedClient);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody ClientDto clientDto) {
        ClientDto updatedClient = clientService.update(id, clientDto);

        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .build(updatedClient.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .location(uri)
                .body(updatedClient);
    }
}
