package com.utm.gitfit.controller;

import com.utm.gitfit.dto.CoachDto;
import com.utm.gitfit.model.response.CoachResponse;
import com.utm.gitfit.service.CoachService;
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
@RequestMapping("/coach")
@Api(value = "Coach API")
public class CoachController {

    private final CoachService coachService;

    @GetMapping
    @ApiOperation(value = "Get all coaches", notes = "Returns all coaches with their ID and personal data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The coaches were retrieved successfully"),
            @ApiResponse(code = 404, message = "Not found - coaches not found")
    })
    public List<CoachResponse> findAll() {
        return coachService.findAll();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get a coach by ID", notes = "Returns the coach with specified ID and their personal data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The coach was retrieved successfully"),
            @ApiResponse(code = 404, message = "Not found - coach not found")
    })
    public CoachResponse findById(@PathVariable @ApiParam(name = "id", value = "coach's id") long id) {
        return coachService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Saves coach", notes = "Coaches are initialized without any clients")
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
    @ApiOperation(value = "Updates coach", notes = "Update the coach with new information")
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
