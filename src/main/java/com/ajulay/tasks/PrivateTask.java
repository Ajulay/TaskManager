package com.ajulay.tasks;

import com.ajulay.executors.Executor;
import com.ajulay.projects.Project;

public class PrivateTask extends AbstractTask {
    private Executor executorName;

    public PrivateTask(String project, String term, int priority, String content, String executorName) {
        super(project, term, priority, content);

    }

    public Executor getExecutorName() {
        return executorName;
    }

    public void setExecutorName(Executor executorName) {
        this.executorName = executorName;
    }
}
