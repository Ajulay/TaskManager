package com.ajulay.api.repository;

import java.util.List;

public interface IRepository<E> {

    E save(E entity);

    E findById(String id);

    E remove(E entity);

    List<E> findAll();

}
