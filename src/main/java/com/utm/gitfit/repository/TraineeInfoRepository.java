package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.TraineeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraineeInfoRepository extends JpaRepository<TraineeInfo, Long> {
}
