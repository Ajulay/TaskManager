package com.ajulay.service;

import com.ajulay.api.dao.IAssignerDao;
import com.ajulay.api.service.IAssignerService;
import com.ajulay.dao.AssignerDao;
import com.ajulay.entity.Assigner;

import java.util.List;

public class AssignerService implements IAssignerService {

    private IAssignerDao assignerDao = new AssignerDao();

    public Assigner createAssigner(String surname) throws Exception {
        return assignerDao.create(surname);
    }

    public Assigner deleteAssigner(String surname) throws Exception {
        return assignerDao.delete(surname);
    }

    public Assigner updateAssigner(Assigner assigner) throws Exception {
        return assignerDao.delete(assigner.getSurname());
    }

    public Assigner getBySurname(String surname) throws Exception {
        return assignerDao.findById(surname);
    }

    public List<Assigner> getAssigners() {
        return assignerDao.findAll();
    }

    public void showAssigners() {
        for (Assigner executor : getAssigners()) {
            System.out.println(executor.getSurname());
        }
    }

}
