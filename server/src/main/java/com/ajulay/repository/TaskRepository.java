package com.ajulay.repository;

import com.ajulay.entity.Task;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository(forEntity = Task.class)
public interface TaskRepository extends EntityRepository<Task, String> {

}
