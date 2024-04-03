package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "task", schema = "public")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;

    @Column(name = "end_date")
    @Temporal(value = TemporalType.DATE)
    private Date end;

    @Column(name = "isfinished")
    private boolean isfinished;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "proj_id")
    @JsonIgnore
    private Project project;

}
