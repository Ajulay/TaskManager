package com.ajulay.task;

import com.ajulay.executor.Executor;
import com.ajulay.service.ExecutorsService;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PrivateTask extends AbstractTask {
    private Executor executor;

    public PrivateTask(String project, String term, int priority, String content, String executorSurname) throws Exception {
        super(project, term, priority, content);
        for (Executor executor : ExecutorsService.getExecutors()) {
            if (executor.getSurname().equals(executorSurname)) {
                this.executor = executor;
                return;
            }
        }
        throw new Exception("no such executor");
    }

    public Executor getExecutorName() {
        return executor;
    }

    public void setExecutorName(Executor executorName) {
        this.executor = executorName;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        .withZone( ZoneId.systemDefault() );
        return "PrivateTask {" +
                "id = " + getId() +
                ", projectName = '" + getProjectName() + '\'' +
                ", term = " + formatter.format(getTerm()) +
                ", priority = " + getPriority() +
                ", content = '" + getContent() + '\'' +
                ", status = " + getStatus() +
                ", executor = " + executor.getSurname() +
                '}';
    }
}
