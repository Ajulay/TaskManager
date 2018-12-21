package com.ajulay.dao;

import com.ajulay.executor.Executor;

import java.util.List;

public interface ExecutorDao {

    Executor create(String surname) throws Exception;

    Executor delete(String surname) throws Exception;

    Executor update(Executor executor) throws Exception;

    Executor findById(String surname) throws Exception;

    List<Executor> getExecutors();
}
