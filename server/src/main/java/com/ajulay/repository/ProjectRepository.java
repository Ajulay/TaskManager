package com.ajulay.repository;

import com.ajulay.entity.Project;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Project.class)
public interface ProjectRepository extends EntityRepository<Project, String> {

    @Query("select p from Project p WHERE p.authorId = ?1")
    List<Project> findAllByUserId(String userId);

}
