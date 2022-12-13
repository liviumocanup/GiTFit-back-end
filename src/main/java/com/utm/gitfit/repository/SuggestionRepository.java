package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.Coach;
import com.utm.gitfit.model.entities.Statistic;
import com.utm.gitfit.model.entities.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    Set<Suggestion> findByStatistic(Statistic statistic);
    Optional<Suggestion> findSuggestionByCoachAndStatistic_Id(Coach coach, Long id);
}
