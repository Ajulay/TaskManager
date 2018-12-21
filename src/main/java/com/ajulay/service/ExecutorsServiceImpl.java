package com.ajulay.service;

import com.ajulay.dao.ExecutorDao;
import com.ajulay.dao.ExecutorDaoImpl;
import com.ajulay.executor.Executor;

import java.util.List;

public class ExecutorsServiceImpl implements ExecutorsService{

  private ExecutorDao dao = new ExecutorDaoImpl();

    public Executor createExecutor(String surname) throws Exception {
        return dao.create(surname);
    }

    public Executor deleteExecutor(String surname) throws Exception {
        return dao.delete(surname);
    }

    public Executor updateExecutor(Executor executor) throws Exception {
        return dao.delete(executor.getSurname());
    }

    public Executor getBySurname(String surname) throws Exception {
        return dao.findById(surname);
    }

    public List<Executor> getExecutors() {
        return dao.getExecutors();
    }

    public void showExecutors() {
        for (Executor executor : getExecutors()) {
            System.out.println(executor.getSurname());
        }
    }
}
