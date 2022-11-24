package com.utm.gitfit.model.entities;

import com.utm.gitfit.model.enums.ConnectionRequestState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ConnectionRequest extends AbstractPersistable<Long> {

    @OneToOne
    private User sourceUser;

    @OneToOne(fetch = FetchType.LAZY)
    private User targetUser;

    @Enumerated(EnumType.STRING)
    private ConnectionRequestState state = ConnectionRequestState.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();

    public ConnectionRequest(User sourceUser, User targetUser) {
        this.sourceUser = sourceUser;
        this.targetUser = targetUser;
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
