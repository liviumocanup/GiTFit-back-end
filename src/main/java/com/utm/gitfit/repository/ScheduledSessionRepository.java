package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.ScheduledSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledSessionRepository extends JpaRepository<ScheduledSession, Long> {
}
