package com.ajulay.dao;

import com.ajulay.data.Data;
import com.ajulay.executor.Executor;
import com.ajulay.service.ExecutorsService;
import com.ajulay.service.ExecutorsServiceImpl;

import java.util.List;

public class ExecutorDaoImpl implements ExecutorDao{

    public List<Executor> getExecutors() {
        return Data.getExecutors();
    }

    @Override
    public Executor create(String surname) throws Exception {
        if (surname == null) throw new Exception("No name");
        final Executor executor = new Executor(surname);
        getExecutors().add(executor);
        return executor;
    }

    @Override
    public Executor delete(String surname) throws Exception {
        for (Executor executor : getExecutors()) {
            if (executor.getName().equals(surname)) {
                getExecutors().remove(executor);
                return executor;
            }
        }
        throw new Exception("No such executor");
    }

    @Override
    public Executor update(Executor executor) throws Exception {
        Executor oldExecutor = findById(executor.getId());
        oldExecutor.setName(executor.getName());
        oldExecutor.setLastName(executor.getLastName());
        oldExecutor.setSurname(executor.getSurname());

        return oldExecutor;
    }

    @Override
    public Executor findById(String id) throws Exception {
        for (Executor executor : getExecutors()) {
            if (executor.getSurname().equals(id)) {
                return executor;
            }
        }
        throw new Exception("No such executor");
    }
}
