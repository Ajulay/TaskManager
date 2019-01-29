package com.ajulay.entity;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.UUID;

@ApplicationScoped
public class Project implements Serializable {

    private String id = UUID.randomUUID().toString();

    private String name;

    private String authorId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
