package com.ajulay.tasks;

import com.ajulay.projects.Project;
import com.sun.tools.javac.code.Attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupTask extends AbstractTask {

    public List<String> executorsSurname;

    public GroupTask(String project, String term, int priority, String content, String... executors) {
        super(project, term, priority, content);
        this.executorsSurname = new ArrayList<>(Arrays.asList(executors));
    }

    public List<String> getExecutors() {
        return executorsSurname;
    }

    public void setExecutors(List<String> executors) {
        this.executorsSurname = executors;
    }

}
