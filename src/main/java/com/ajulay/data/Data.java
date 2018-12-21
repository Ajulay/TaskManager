package com.ajulay.data;

import com.ajulay.executor.Executor;
import com.ajulay.project.Project;
import com.ajulay.task.AbstractTask;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static final List<AbstractTask> tasks = new ArrayList<>();

    private static final List<Executor> executors = new ArrayList<>();

    private static final List<Project> projects = new ArrayList<>();

    public synchronized static List<AbstractTask> getTasks() {
        return tasks;
    }

    public synchronized static List<Executor> getExecutors() {
        return executors;
    }

    public synchronized static List<Project> getProjects() {
        return projects;
    }
}
