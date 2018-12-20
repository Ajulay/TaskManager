package com.ajulay.task;

import com.ajulay.executor.Executor;
import com.ajulay.service.ExecutorsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupTask extends AbstractTask {

    public List<String> executorsSurname;

    public GroupTask(String project, String term, int priority, String content, String... executorsSurname) throws Exception {
        super(project, term, priority, content);
        boolean isExecutorExist = false;
        for (Executor ex : ExecutorsService.getExecutors()) {
            for (String surname : executorsSurname) {
                if(ex.getSurname().equals(surname)){
                    isExecutorExist = true;
                }
            }
        }
        if(!isExecutorExist) throw new Exception("No such executor");
        this.executorsSurname = new ArrayList<>(Arrays.asList(executorsSurname));
    }

    public List<String> getExecutors() {
        return executorsSurname;
    }

    public void setExecutors(List<String> executors) {
        this.executorsSurname = executors;
    }

}
