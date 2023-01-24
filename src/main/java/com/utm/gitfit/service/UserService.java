package com.utm.gitfit.service;

import com.utm.gitfit.exception.EntityInvalidInputException;
import com.utm.gitfit.mapper.ConnectionRequestMapper;
import com.utm.gitfit.mapper.UserMapper;
import com.utm.gitfit.model.dto.PendingConnectionRequest;
import com.utm.gitfit.model.entities.ConnectionRequest;
import com.utm.gitfit.model.entities.User;
import com.utm.gitfit.model.enums.ConnectionRequestAnswer;
import com.utm.gitfit.model.enums.ConnectionRequestState;
import com.utm.gitfit.model.response.UserResponse;
import com.utm.gitfit.repository.ConnectionRequestRepository;
import com.utm.gitfit.repository.UserRepository;
import com.utm.gitfit.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ConnectionRequestRepository connectionRequestRepository;
    private final ConnectionRequestMapper connectionRequestMapper;
    private final UserMapper userMapper;

    public Long getCurrentUserId(){
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.id();
    }

    @Transactional(readOnly = true)
    public User getCurrentUser(){
        return userRepository.findById(getCurrentUserId()).orElseThrow();
    }

    @Transactional(readOnly = true)
    public User getById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public UserResponse findById(Long id){
        return userMapper.mapEntityToResponse(getById(id));
    }

    @Transactional
    public void createConnectionRequestFromCurrentUser(Long to) {
        User currentUser = getCurrentUser();
        User targetUser = getById(to);
        if(currentUser.equals(targetUser))
            throw new EntityInvalidInputException("Cannot connect with yourself.");
        ConnectionRequest connectionRequest = new ConnectionRequest(currentUser, targetUser);
        connectionRequestRepository.save(connectionRequest);
    }

    @Transactional(readOnly = true)
    public List<PendingConnectionRequest> getPendingConnectionRequests() {
        List<ConnectionRequest> pendingRequests = connectionRequestRepository.findAllByTargetUser_IdAndState(getCurrentUserId(), ConnectionRequestState.PENDING);
        return connectionRequestMapper.map(pendingRequests);
    }

    @Transactional
    public void answerConnectionRequest(Long requestId, ConnectionRequestAnswer connectionRequestAnswer) {
        ConnectionRequest connectionRequest = connectionRequestRepository.findById(requestId).orElseThrow();
        ConnectionRequestState newState = switch (connectionRequestAnswer){
            case ACCEPT -> ConnectionRequestState.ACCEPTED;
            case DECLINE -> ConnectionRequestState.DECLINED;
        };
        connectionRequest.setState(newState);
        connectionRequestRepository.save(connectionRequest);
        if(connectionRequestAnswer == ConnectionRequestAnswer.ACCEPT){
            User sendingUser = connectionRequest.getSourceUser();
            User targetUser = (User) Hibernate.unproxy(connectionRequest.getTargetUser());
            sendingUser.addConnection(targetUser);
            targetUser.addConnection(sendingUser);
            userRepository.save(sendingUser);
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
