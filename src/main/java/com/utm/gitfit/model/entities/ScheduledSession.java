package com.utm.gitfit.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ScheduledSession extends AbstractPersistable<Long> {

    @ManyToOne
    private Coach coach;

    @ManyToOne
    private Client client;

    private LocalDateTime localDateTime;

    private Integer duration;
}
