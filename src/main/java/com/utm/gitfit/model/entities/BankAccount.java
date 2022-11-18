package com.utm.gitfit.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
