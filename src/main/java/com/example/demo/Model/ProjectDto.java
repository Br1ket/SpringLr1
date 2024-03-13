package com.example.demo.Model;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate begin;
    private LocalDate end;

    public Project clone() {
        return new Project(0,name,description,begin,end);
    }
}
