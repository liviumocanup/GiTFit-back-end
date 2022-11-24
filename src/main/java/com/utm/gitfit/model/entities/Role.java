package com.utm.gitfit.model.entities;

import com.utm.gitfit.model.enums.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")})
public class Role extends AbstractPersistable<Long> {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}