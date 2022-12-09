package com.utm.gitfit.controller;

import com.utm.gitfit.model.client.ApiException;
import com.utm.gitfit.model.client.model.PaymentTokenResponse;
import com.utm.gitfit.model.dto.CoachDetailsDto;
import com.utm.gitfit.model.entities.ScheduledSession;
import com.utm.gitfit.model.request.ScheduleRequest;
import com.utm.gitfit.service.CoachServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/coach/")
public class CoachController {

    private final CoachServiceImpl coachService;

    @PostMapping("/schedule")
    public PaymentTokenResponse scheduleSession(@RequestBody ScheduleRequest scheduleRequest) throws ApiException {
        return coachService.scheduleSession(scheduleRequest);
    }

    @GetMapping("/schedule")
    public List<ScheduledSession> getAllScheduledSessionsByDate(@RequestParam LocalDate date){
        return coachService.getAllScheduledSessionsByDate(date);
    }

    @PutMapping("/details")
    public void addCoachDetails(@RequestBody CoachDetailsDto coachDetailsDto){
        coachService.addCoachDetails(coachDetailsDto);
    }

}
