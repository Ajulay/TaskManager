package com.ajulay.repository;

import com.ajulay.api.repository.IRepository;
import com.ajulay.entity.Project;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Project.class)
public interface ProjectRepository extends IRepository<Project> {

    @Query("select p from Project p WHERE p.authorId = ?1")
    List<Project> findAllByUserId(String userId);

    @Query("SELECT p FROM Project p WHERE p.id = ?1")
    Project findById(String id);

    Project save(Project entity);

    Project remove(Project entity);

    Project update(Project entity);

    List<Project> findAll();

}
