package com.example.demo.Service;

import com.example.demo.Model.Project;
import com.example.demo.Model.ProjectDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface ProjectServiceI {

    public Optional<ProjectDto> create(String name, String description, LocalDate begin, LocalDate end);


    public Optional<ProjectDto> getById(Long id);

    public boolean update(ProjectDto project, ProjectDto pTemp);

    public int delete(Long id);

    public Set<ProjectDto> getByRange(LocalDate begin, LocalDate end);
    public Set<ProjectDto> getAllProject();
}
