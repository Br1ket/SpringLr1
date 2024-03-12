package com.example.demo.Repository;

import com.example.demo.Model.Project;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface ProjectRepositoryI {

    public Optional<Project> getById(Long id);

    public int delete(Long id);

    public boolean update(Project project);
    public Optional<Project> create(String name, String description, LocalDate begin, LocalDate end);

    public Set<Project> getAllProject();
    public Set<Project> getByRange(LocalDate begin, LocalDate end);
}
