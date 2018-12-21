package com.ajulay.service;

import com.ajulay.dao.ExecutorDao;
import com.ajulay.dao.ExecutorDaoImpl;
import com.ajulay.executor.Executor;

import java.util.List;

public class ExecutorsService {

  private static ExecutorDao dao = new ExecutorDaoImpl();

    public static Executor createExecutor(String surname) throws Exception {
        return dao.create(surname);
    }

    public static Executor deleteExecutor(String surname) throws Exception {
        return dao.delete(surname);
    }

    public static Executor updateExecutor(Executor executor) throws Exception {
        return dao.delete(executor.getSurname());
    }

    public static Executor getBySurname(String surname) throws Exception {
        return dao.findById(surname);
    }

    public static List<Executor> getExecutors() {
        return dao.getExecutors();
    }

    public static void showExecutors() {
        for (Executor executor : ExecutorsService.getExecutors()) {
            System.out.println(executor.getSurname());
        }
    }
}
