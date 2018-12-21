package com.ajulay.dao;

import com.ajulay.data.Data;
import com.ajulay.executor.Executor;
import com.ajulay.service.ExecutorsService;

import java.util.List;

public class ExecutorDaoImpl implements ExecutorDao{

    private static final List<Executor> executors = Data.getExecutors();

    public List<Executor> getExecutors() {
        return executors;
    }

    @Override
    public Executor create(String surname) throws Exception {
        if (surname == null) throw new Exception("No name");
        final Executor executor = new Executor(surname);
        executors.add(executor);
        return executor;
    }

    @Override
    public Executor delete(String surname) throws Exception {
        for (Executor executor : executors) {
            if (executor.getName().equals(surname)) {
                executors.remove(executor);
                return executor;
            }
        }
        throw new Exception("No such executor");
    }

    @Override
    public Executor update(Executor executor) throws Exception {
        Executor oldExecutor = ExecutorsService.getBySurname(executor.getSurname());
        oldExecutor.setName(executor.getName());
        oldExecutor.setLastName(executor.getLastName());
        oldExecutor.setTasks(executor.getTasks());
        return oldExecutor;
    }

    @Override
    public Executor findById(String surname) throws Exception {
        for (Executor executor : executors) {
            if (executor.getSurname().equals(surname)) {
                return executor;
            }
        }
        throw new Exception("No executor");
    }
}
