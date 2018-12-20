package com.ajulay.task;

import com.ajulay.executor.Executor;
import com.ajulay.service.ExecutorsService;

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
}
