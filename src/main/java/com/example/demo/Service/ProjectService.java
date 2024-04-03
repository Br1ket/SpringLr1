package com.example.demo.Service;

import com.example.demo.Entity.Project;
import com.example.demo.Repository.ProjectRepositoryI;
import com.example.demo.Repository.TaskRepositoryI;
import com.example.demo.pojo.ProjectPojo;
import com.example.demo.pojo.TaskPojo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService implements ProjectServiceI{

    @Autowired
    private final ProjectRepositoryI projectRepository;

    @Autowired
    private final TaskRepositoryI taskRepository;

    //    Создание проекта.
    //    POST /projects
    //    Должен вернуть 201 код в случае успешного создания проекта, а также сущность созданного проекта.

    @Override
    public Optional<ProjectPojo> create(ProjectPojo pojo) {

        pojo.setTasks(new ArrayList<>());
        Project project = projectRepository.save(ProjectPojo.toEntity(pojo));

        return Optional.of(ProjectPojo.fromEntity(project));
    }

    @Override
    public Optional<ProjectPojo> getById(Long id) {
        Optional<Project> project = projectRepository.findById(Math.toIntExact(id));

        if(project.isEmpty()) return Optional.empty();
        else {
            return Optional.of(ProjectPojo.fromEntity(project.get()));
        }
    }

    @Override
    public Optional<ProjectPojo> update(ProjectPojo project, ProjectPojo pTemp) {
        Project p = ProjectPojo.toEntity(project);

        p.setId(pTemp.getId());
        System.out.println(p);
        System.out.println(pTemp);
        if (p.getName() == null) {
            p.setName(pTemp.getName());
        }
        if (p.getDescription() == null) {
            p.setDescription(pTemp.getDescription());
        }
        if (p.getBegin() == null) {
            p.setBegin(pTemp.getBegin());
        }
        if (p.getEnd() == null) {
            p.setEnd(pTemp.getEnd());
        }

        p.setTasks(pTemp.getTasks().stream().map(TaskPojo::toEntity).toList());
        System.out.println(p);
        return Optional.of(ProjectPojo.fromEntity(projectRepository.save(p)));
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(Math.toIntExact(id));
        taskRepository.deleteAllByProjectId(Math.toIntExact(id));
    }

    @Override
    public List<ProjectPojo> getAllProject() {
       List<Project> project = projectRepository.findAll();
       return project.stream().map(ProjectPojo::fromEntity).toList();
    }

    public List<ProjectPojo> getProjectByDescFilter(String phrase) {
        return projectRepository.findByNameIsContainingIgnoreCaseOrDescriptionIsContainingIgnoreCase(phrase, phrase)
                .stream().map(ProjectPojo::fromEntity).toList();
    }

    public HashMap<Integer, Long> getNotFulfilledTask() {
        List<Object[]> result = projectRepository.findProjectsAndTaskCount();
        HashMap<Integer, Long> openedTaskDict = new HashMap<>(result.size());
        System.out.println("+");
        for (int i = 0; i < result.size(); i++) openedTaskDict.put(Math.toIntExact((Long) result.get(i)[0]), (Long) result.get(i)[1]);
        System.out.println("-");
        return openedTaskDict;
    }
}
