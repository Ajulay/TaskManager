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

    public static void updateExecutor(String oldName, String newName) {
        for (int i = 0; i < executors.size(); i++) {
            if (executors.get(i).getName().equals(oldName)) {
                executors.get(i).setName(newName);
            }
        }
    }

    public static Executor getByName(String name) throws Exception {
        for (Executor executor : executors) {
            if (executor.getName().equals(name)) {
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
