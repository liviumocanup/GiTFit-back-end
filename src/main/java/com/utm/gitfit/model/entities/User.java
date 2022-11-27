package com.utm.gitfit.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity
@Table(name = "gitfit_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")})
@Inheritance(strategy = InheritanceType.JOINED)
@Accessors(chain = true)
@Getter
@Setter
public abstract class User extends AbstractPersistable<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "birthday")
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BillingDetails billingDetails;

    @Column(name = "password")
    @Size(max = 120)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role userRole;

    private String saltEdgeIdentifier;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public abstract void addConnection(User user);
}
