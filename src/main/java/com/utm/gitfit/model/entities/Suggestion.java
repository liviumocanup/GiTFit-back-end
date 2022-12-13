package com.utm.gitfit.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Suggestion extends AbstractPersistable<Long> {
    @ManyToOne
    private Statistic statistic;

    @OneToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    private String suggestion;
}
