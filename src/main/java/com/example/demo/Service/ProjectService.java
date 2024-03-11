package com.example.demo.Service;

import com.example.demo.ProjectDto;
import com.example.demo.Repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService implements ProjectServiceI{

    @Autowired
    private final ProjectRepository projectRepository;


    public Optional<ProjectDto> getById(Long id) {
        var dbo = projectRepository.getById(id);
        if (dbo.isPresent()) {
            var mod = dbo.get();
            var pojo = new ProjectDto(mod.getId(), mod.getName(), mod.getDescription(), mod.getBegin(), mod.getEnd());
            return Optional.of(pojo);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
            return projectRepository.delete(id);
    }

}
