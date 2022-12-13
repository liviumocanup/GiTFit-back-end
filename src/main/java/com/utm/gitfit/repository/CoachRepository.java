package com.utm.gitfit.repository;

import com.utm.gitfit.model.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    //@Query("SELECT * FROM Coach WHERE UPPER(CONCAT(name, ' ', lastName)) LIKE UPPER('%:name%')")
    List<Coach> findByNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(String name, String lastname);

}
