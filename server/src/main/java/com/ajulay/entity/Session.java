package com.ajulay.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "session")
@Getter
@Setter
@NoArgsConstructor
public class Session {

    @Id
    private String id = UUID.randomUUID().toString();

    private String userId;

    private String signature;

    private Date createdDate = new Date();

    public String getId() {
        return id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull String userId) {
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return Objects.equals(getId(), session.getId()) &&
                Objects.equals(getSignature(), session.getSignature()) &&
                Objects.equals(getUserId(), session.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSignature(), getCreatedDate());
    }

}
