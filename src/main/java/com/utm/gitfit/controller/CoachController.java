package com.utm.gitfit.controller;

import com.utm.gitfit.dto.CoachDto;
import com.utm.gitfit.model.response.CoachResponse;
import com.utm.gitfit.service.ClientService;
import com.utm.gitfit.service.CoachService;
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
@RequestMapping("/coach")
public class CoachController {

    private final CoachService coachService;

    @GetMapping
    public List<CoachResponse> findAll() {
        return coachService.findAll();
    }

//    @Operation(summary = "Get a coach by their id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the coach",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = CoachService.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
//                    content = @Content),
//            @ApiResponse(responseCode = "404", description = "Coach not found",
//                    content = @Content)})
    @GetMapping("{id}")
    public CoachResponse findById(//@Parameter(description = "id of coach to be searched")
                                  @PathVariable long id) {
        return coachService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CoachResponse> create(@Valid @RequestBody CoachDto coachDto) {
        CoachResponse savedCoach = coachService.save(coachDto);

        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .build(savedCoach.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(savedCoach);
    }

    @PutMapping
    public ResponseEntity<CoachResponse> update(@Valid @RequestBody CoachDto coachDto) {
        CoachResponse updatedCoach = coachService.update(coachDto);

        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .build(updatedCoach.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .location(uri)
                .body(updatedCoach);
    }
}
