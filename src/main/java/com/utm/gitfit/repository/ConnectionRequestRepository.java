package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.ConnectionRequest;
import com.utm.gitfit.model.enums.ConnectionRequestState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Long> {

    List<ConnectionRequest> findAllByTargetUser_IdAndState(Long userId, ConnectionRequestState connectionRequestState);
}
