package com.ajulay.service;

import com.ajulay.executor.Executor;

import java.util.List;

public interface ExecutorsService {

    Executor createExecutor(String surname) throws Exception;

    Executor deleteExecutor(String surname) throws Exception;

    Executor updateExecutor(Executor executor) throws Exception;

    Executor getBySurname(String surname) throws Exception;

    void showExecutors();

    List<Executor> getExecutors();

}
