package com.example.demo.Service;

import com.example.demo.Model.Project;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface ProjectServiceI {
    public Optional<Project> getById(Long id);
    public Optional<Project> create(String name, String description, LocalDate begin, LocalDate end);
    public int delete(Long id);

    public boolean update(Project project);
    public Set<Project> getByRange(LocalDate begin, LocalDate end);

    public Set<Project> getAllProject();
}
