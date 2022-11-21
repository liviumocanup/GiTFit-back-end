package com.utm.gitfit.controller;

import com.utm.gitfit.dto.CoachDto;
import com.utm.gitfit.model.response.CoachResponse;
import com.utm.gitfit.service.CoachService;
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

    @GetMapping("{id}")
    public CoachResponse findById(@PathVariable long id) {
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

    @PostMapping
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
