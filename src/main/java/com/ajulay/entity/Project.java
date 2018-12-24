package com.ajulay.entity;

import java.util.UUID;

public class Project {

    private final String id = UUID.randomUUID().toString();

    private String name;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
