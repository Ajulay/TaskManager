package com.ajulay.dao;

import com.ajulay.api.dao.IAssignerDAO2;
import com.ajulay.entity.Assigner;

import java.util.ArrayList;
import java.util.List;

public class AssignerDAO2 implements IAssignerDAO2 {

    private final List<Assigner> assigners = new ArrayList<>();

    @Override
    public Assigner create(String surname) throws Exception {
        if (surname == null) throw new Exception("No name");
        final Assigner executor = new Assigner(surname);
        findAll().add(executor);
        return executor;
    }

    @Override
    public Assigner delete(String surname) throws Exception {
        for (Assigner executor : findAll()) {
            if (executor.getName().equals(surname)) {
                findAll().remove(executor);
                return executor;
            }
        }
        throw new Exception("No such executor");
    }

    @Override
    public Assigner update(Assigner assigner) throws Exception {
        Assigner oldAssigner = findById(assigner.getId());
        oldAssigner.setName(assigner.getName());
        oldAssigner.setLastName(assigner.getLastName());
        oldAssigner.setSurname(assigner.getSurname());
        return oldAssigner;
    }

    @Override
    public Assigner findById(String id) throws Exception {
        for (Assigner assigner : findAll()) {
            if (assigner.getSurname().equals(id)) {
                return assigner;
            }
        }
        throw new Exception("No such executor");
    }

    @Override
    public List<Assigner> findAll() {
        return assigners;
    }
}
