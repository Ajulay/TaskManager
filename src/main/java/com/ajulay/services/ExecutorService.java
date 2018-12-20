package com.ajulay.services;

import com.ajulay.executors.Executor;

import java.util.ArrayList;
import java.util.List;

public class ExecutorService {
    private static final List<Executor> executors = new ArrayList<>();

    public static Executor create(String name){
    Executor executor = new Executor(name);
    executors.add(executor);
        return executor;
    }

    public static Executor delete(String name) throws Exception {

        for (int i = 0; i < executors.size(); i++) {
            if (executors.get(i).getName().equals(name)){
                executors.remove(executors.get(i));
                return executors.get(i);
            }
        }
        throw new Exception("No executor");
    }

    public static void update(String oldName, String newName){
        for (int i = 0; i < executors.size(); i++) {
            if (executors.get(i).getName().equals(oldName)){
                executors.get(i).setName(newName);
            }
        }
    }

    public static Executor getByName(String name) throws Exception {
        for (int i = 0; i < executors.size(); i++) {
            if (executors.get(i).getName().equals(name)){
                return executors.get(i);
            }
        }
        throw new Exception("No executor");
    }

    public static List<Executor> getExecutors() {
        return executors;
    }

    public static void showExecutors() {
        for (Executor executor: ExecutorService.getExecutors()) {
            System.out.println(executor.getSurname());
        }
    }
}
