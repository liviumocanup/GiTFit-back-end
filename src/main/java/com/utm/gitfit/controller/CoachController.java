package com.utm.gitfit.controller;

import com.utm.gitfit.model.response.CoachResponse;
import com.utm.gitfit.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
