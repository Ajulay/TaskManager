package com.ajulay.executor;

import com.ajulay.task.AbstractTask;

import java.util.List;

public class Executor {

    private String name;

    private String surname;

    private String lastName;

    private List<AbstractTask> tasks;

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

    public List<AbstractTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<AbstractTask> tasks) {
        this.tasks = tasks;
    }
}
