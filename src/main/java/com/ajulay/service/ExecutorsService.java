package com.ajulay.service;

import com.ajulay.executor.Executor;

import java.util.ArrayList;
import java.util.List;

public class ExecutorsService {

    private static final List<Executor> executors = new ArrayList<>();

    public static Executor createExecutor(String name) throws Exception {
        if (name == null) throw new Exception("No name");
        final Executor executor = new Executor(name);
        executors.add(executor);
        return executor;
    }

    public static Executor deleteExecutor(String name) throws Exception {
        for (Executor executor : executors) {
            if (executor.getName().equals(name)) {
                executors.remove(executor);
                return executor;
            }
        }
        throw new Exception("No executor");
    }

    public static void updateExecutor(Executor executor) throws Exception {
        Executor oldExecutor = ExecutorsService.getBySurname(executor.getSurname());
        oldExecutor.setName(executor.getName());
        oldExecutor.setLastName(executor.getLastName());
        oldExecutor.setTasks(executor.getTasks());
    }

    public static Executor getBySurname(String surname) throws Exception {
        for (Executor executor : executors) {
            if (executor.getSurname().equals(surname)) {
                return executor;
            }
        }
        throw new Exception("No executor");
    }

    public static List<Executor> getExecutors() {
        return executors;
    }

    public static void showExecutors() {
        for (Executor executor : ExecutorsService.getExecutors()) {
            System.out.println(executor.getSurname());
        }
    }
}
