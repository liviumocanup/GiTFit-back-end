package com.utm.gitfit.controller;

import com.utm.gitfit.model.dto.PendingConnectionRequest;
import com.utm.gitfit.model.enums.ConnectionRequestAnswer;
import com.utm.gitfit.model.response.UserResponse;
import com.utm.gitfit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/connect/{targetUserId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendConnectionRequestFromCurrentUserToAnotherUser(@PathVariable("targetUserId") Long to){
        userService.createConnectionRequestFromCurrentUser(to);
    }

    @GetMapping("/connect/pending")
    public List<PendingConnectionRequest> getPendingConnectionRequests(){
        return userService.getPendingConnectionRequests();
    }

    @PostMapping("/connect/pending/{requestId}")
    public void answerConnectionRequest(@PathVariable Long requestId, @RequestParam("requestAnswer") ConnectionRequestAnswer connectionRequestAnswer){
        userService.answerConnectionRequest(requestId, connectionRequestAnswer);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id){
        return userService.findById(id);
    }
}
