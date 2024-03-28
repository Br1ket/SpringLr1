package com.example.demo.Repository;

import com.example.demo.Model.Project;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface ProjectRepositoryI {
    Optional<Project> getById(Long id);

    Optional<Project> create(String name, String description, LocalDate begin, LocalDate end);

    Set<Project> getAllProject();

    int delete(Long id);

    boolean update(Project project);

    Set<Project> getByRange(LocalDate begin, LocalDate end);
}
