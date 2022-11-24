package com.utm.gitfit.controller;

import com.utm.gitfit.dto.ClientDto;
import com.utm.gitfit.model.response.ClientResponse;
import com.utm.gitfit.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Client API")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @ApiOperation(value = "Get all clients", notes = "Returns all clients with their ID and personal data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The clients were retrieved successfully"),
            @ApiResponse(code = 404, message = "Not found - clients not found")
    })
    public List<ClientResponse> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a client by ID", notes = "Returns the client with specified ID and their personal data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The client was retrieved successfully"),
            @ApiResponse(code = 404, message = "Not found - client not found")
    })
    public ClientResponse findById(@PathVariable @ApiParam(name = "id", value = "client's id") long id) {
        return clientService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Saves client", notes = "Clients are initialized without a coach")
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
    @ApiOperation(value = "Updates client", notes = "Update the client with new information")
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
