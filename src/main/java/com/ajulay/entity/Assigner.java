package com.ajulay.entity;

import java.util.UUID;

public class Assigner {

    private final String id = UUID.randomUUID().toString();

    private String name;

    private String surname;

    private String lastName;

    public Assigner() {
    }

    public Assigner(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }
}
