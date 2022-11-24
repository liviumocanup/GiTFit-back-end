package com.utm.gitfit.model.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "bank_account")
public class BillingDetails extends AbstractPersistable<Long> {

    private String town;

    private String street;

    private String countryCode;

    private String iban;

    private String postCode;

    @OneToOne(mappedBy = "billingDetails")
    private User user;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
