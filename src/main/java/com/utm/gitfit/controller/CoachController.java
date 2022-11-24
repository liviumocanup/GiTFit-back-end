package com.utm.gitfit.controller;

import com.utm.gitfit.model.entities.ScheduledSession;
import com.utm.gitfit.model.request.ScheduleRequest;
import com.utm.gitfit.model.response.CoachResponse;
import com.utm.gitfit.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/coach/")
public class CoachController {

    private final CoachService coachService;

    @PostMapping("/schedule")
    public void scheduleSession(@RequestBody ScheduleRequest scheduleRequest){
        coachService.scheduleSession(scheduleRequest);
    }

    @GetMapping("/schedule")
    public List<ScheduledSession> getAllScheduledSessionsByDate(@RequestParam LocalDate date){
        return coachService.getAllScheduledSessionsByDate(date);
    }

}
