package com.utm.gitfit.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "bankAccount", cascade = ALL, orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<CardCredentials> cardCredentialsList;
}
