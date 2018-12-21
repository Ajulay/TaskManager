package com.ajulay.executor;

import com.ajulay.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Executor {

    private final String id = UUID.randomUUID().toString();

    private final List<Task> tasks = new ArrayList<>();

    private String name;

    private String surname;

    private String lastName;

    public Executor() {
    }

    public Executor(String surname) {
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

    public List<Task> getTasks() {
        return tasks;
    }

    public String getId() {
        return id;
    }
}
