package com.utm.gitfit.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "client")
public class Client extends User{

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private Coach coach;
}
