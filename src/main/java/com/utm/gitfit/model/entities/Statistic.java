package com.utm.gitfit.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Statistic extends AbstractPersistable<Long> {
    @ManyToOne
    private Client client;

    private LocalDate date;

    private Double weight;

    private Double height;

    private Double fatRatio;

    @OneToMany(mappedBy = "statistic", cascade = CascadeType.ALL)
    private Set<Suggestion> suggestions;

    private String diet;
}
