package com.example.demo.Repository;

import com.example.demo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepositoryI extends JpaRepository<Project, Integer> {
    public Optional<Project> findById(Integer projectId);
    public List<Project> findByNameIsContainingIgnoreCaseOrDescriptionIsContainingIgnoreCase(String name, String description);

    @Query(value = "select projects.project_id, (select count(task) from task where task.isfinished = False and projects.project_id = task.proj_id) from projects",
            nativeQuery = true)
    public List<Object[]> findProjectsAndTaskCount();
}
