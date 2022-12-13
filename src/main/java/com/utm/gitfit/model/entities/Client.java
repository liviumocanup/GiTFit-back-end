package com.utm.gitfit.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends User {

    @ManyToMany
    @JoinTable(name = "client_coach",
            joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "coach_id"))
    private Set<Coach> coaches = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<ScheduledSession> scheduledSessions;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Statistic> statistics;

    @Override
    public void addConnection(User user) {
        coaches.add((Coach) user);
    }
}
