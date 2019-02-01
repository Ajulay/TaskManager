package com.ajulay.entity;

import com.ajulay.enumirated.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Entity
@ApplicationScoped
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class Task implements Serializable {

    @Id
    @Nullable
    private String id = UUID.randomUUID().toString();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @Temporal(TemporalType.TIMESTAMP)
    private Date term;

    private int priority;

    @Column(name = "task_content")
    private String content;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.START;

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        .withZone( ZoneId.systemDefault() );
        return "Task {" +
                "id = " + id +
                ", projectId = '" + project.getId() + '\'' +
                ", term = " + term.toString() +
                ", priority = " + priority +
                ", content = '" + content + '\'' +
                ", status = " + status +
                '}';
    }

}
