package com.utm.gitfit.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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

    @Override
    public void addConnection(User user) {
        clients.add((Client) user);
    }
}
