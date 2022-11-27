package com.utm.gitfit.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "coach")
public class Coach extends User{

    @ManyToMany(mappedBy = "coaches")
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private Set<ScheduledSession> scheduledSessions;

    private Double ratePerHour;

    private String gymAddress;

    private String aboutMe;

    @Override
    public void addConnection(User user) {
        clients.add((Client) user);
    }
}
