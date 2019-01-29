package com.ajulay.entity;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@ApplicationScoped
public class Session {

    private String id = UUID.randomUUID().toString();

    private String UserId;

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
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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
