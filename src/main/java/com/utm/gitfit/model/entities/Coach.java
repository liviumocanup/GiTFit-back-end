package com.utm.gitfit.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "coach")
public class Coach extends User{

    @OneToMany(mappedBy = "coach")
    private List<Client> clients = new ArrayList<>();
}
