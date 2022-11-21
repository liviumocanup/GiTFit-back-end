package com.utm.gitfit.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.YearMonth;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "card_credentials")
public class CardCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private BankAccount bankAccount;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "expiry_date")
    private YearMonth expiryDate;
}
