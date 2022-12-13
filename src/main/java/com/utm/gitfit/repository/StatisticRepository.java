package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.Client;
import com.utm.gitfit.model.entities.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    Set<Statistic> findByClient(Client client);
    Optional<Statistic> findStatisticByClientAndDate(Client client, LocalDate date);
}
