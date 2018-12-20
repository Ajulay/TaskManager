package com.ajulay.task;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class OveralTask extends AbstractTask {

    public OveralTask(String project, String term, int priority, String content) {
        super(project, term, priority, content);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        .withZone( ZoneId.systemDefault() );
        return "OveralTask {" +
                "id=" + getId() +
                ", projectName='" + getProjectName() + '\'' +
                ", term=" + formatter.format(getTerm()) +
                ", priority=" + getPriority() +
                ", content='" + getContent() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
