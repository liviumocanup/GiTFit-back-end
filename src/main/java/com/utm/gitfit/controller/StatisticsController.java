package com.utm.gitfit.controller;

import com.utm.gitfit.model.request.StatisticRequest;
import com.utm.gitfit.model.request.SuggestionRequest;
import com.utm.gitfit.model.response.StatisticResponse;
import com.utm.gitfit.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticService statisticService;

    @GetMapping("/{clientId}/all")
    public Set<StatisticResponse> findByClient(@PathVariable long clientId) {
        return statisticService.findByClient(clientId);
    }

    @GetMapping("/{clientId}")
    public StatisticResponse findByClientAndDay(@PathVariable long clientId,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return statisticService.findByClientAndDay(clientId, date);
    }

    @PostMapping("/{clientId}")
    public StatisticResponse save(@PathVariable long clientId, @RequestBody StatisticRequest statisticDto) {
        return statisticService.create(clientId, statisticDto);
    }

    @PostMapping("/{clientId}/review")
    public StatisticResponse review(@PathVariable long clientId, @RequestBody SuggestionRequest suggestionRequest) {
        return statisticService.review(clientId, suggestionRequest);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable long clientId,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        statisticService.remove(clientId, date);
    }
}
