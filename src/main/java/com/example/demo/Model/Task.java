package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "task", schema = "public")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private String name;

    @Temporal(value = TemporalType.DATE)
    private Date end;

    private boolean isfinished;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "prjid", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Project project;

}
