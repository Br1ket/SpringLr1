package com.example.demo.Service;

import com.example.demo.pojo.ProjectPojo;

import java.util.List;
import java.util.Optional;

public interface ProjectServiceI {

    public Optional<ProjectPojo> create(ProjectPojo pojo);

    public Optional<ProjectPojo> getById(Long id);

    public Optional<ProjectPojo> update(ProjectPojo project, ProjectPojo pTemp);

    public void delete(Long id);

    public List<ProjectPojo> getAllProject();
}
