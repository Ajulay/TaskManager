package com.ajulay.repository;

import com.ajulay.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("select p from Project p WHERE p.authorId = ?1")
    List<Project> findAllByUserId(String userId);

}
