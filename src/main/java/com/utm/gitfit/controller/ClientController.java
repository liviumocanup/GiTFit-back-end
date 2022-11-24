package com.utm.gitfit.controller;

import com.utm.gitfit.dto.ClientDto;
import com.utm.gitfit.model.response.ClientResponse;
import com.utm.gitfit.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

//    @Operation(summary = "Get a client by their id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the client",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ClientService.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
//                    content = @Content),
//            @ApiResponse(responseCode = "404", description = "Client not found",
//                    content = @Content)})
    @GetMapping("/{id}")
    public ClientResponse findById(//@Parameter(description = "id of client to be searched")
                                   @PathVariable long id) {
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
