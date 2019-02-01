package com.ajulay.dto;

import com.ajulay.enumirated.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskView {

    private String id;

    private String projectId;

    private Date term;

    private int priority;

    private String content;

    private Status status;

}
