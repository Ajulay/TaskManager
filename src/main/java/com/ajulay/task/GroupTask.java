package com.ajulay.task;

import com.ajulay.executor.Executor;
import com.ajulay.service.ExecutorsService;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupTask extends AbstractTask {

    public List<String> executorsSurnames;

    public GroupTask(String project, String term, int priority, String content, String... executorsSurname) throws Exception {
        super(project, term, priority, content);
        boolean isExecutorExist = false;
        for (Executor ex : ExecutorsService.getExecutors()) {
            for (String surname : executorsSurname) {
                if (ex.getSurname().equals(surname)) {
                    isExecutorExist = true;
                }
            }
        }
        if (!isExecutorExist) throw new Exception("No such executor");
        this.executorsSurnames = new ArrayList<>(Arrays.asList(executorsSurname));
    }

    public List<String> getExecutors() {
        return executorsSurnames;
    }

    public void setExecutors(List<String> executors) {
        this.executorsSurnames = executors;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        .withZone(ZoneId.systemDefault());
        String fullNamesString = "";
        for (String name : executorsSurnames) {
            fullNamesString += name + ", ";
        }
        fullNamesString = fullNamesString.substring(0, fullNamesString.length() - 2);
        return "GroupTask {" +
                "id = " + getId() +
                ", projectName = '" + getProjectName() + '\'' +
                ", term = " + formatter.format(getTerm()) +
                ", priority = " + getPriority() +
                ", content = '" + getContent() + '\'' +
                ", status = " + getStatus() +
                ", executors = " + fullNamesString +
                '}';
    }
}
