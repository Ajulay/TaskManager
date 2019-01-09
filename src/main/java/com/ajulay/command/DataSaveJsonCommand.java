package com.ajulay.command;

import com.ajulay.entity.Assignee;
import com.ajulay.entity.Project;
import com.ajulay.entity.Task;
import com.ajulay.entity.User;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataSaveJsonCommand extends AbstractCommand {

    public static final String COMMAND = "/json";

    @Override
    public String getCommandKeyWord() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "save current data to file";
    }

    @Override
    public void execute() throws Exception {
        final Path dir = Paths.get("dataJson");
        final Path path = Paths.get(dir.toString(), "AppDataJson.txt");
        if (path.toFile().exists()) Files.delete(path);
        if (dir.toFile().exists()) Files.delete(dir);
        Files.createDirectories(dir);
        Files.createFile(path);
        final JsonObjectBuilder lastBuilder = Json.createObjectBuilder();
        final JsonObjectBuilder resultBuilder = Json.createObjectBuilder();
        final List<Project> projects = getController().getProjectService().getProjects();
        final JsonArrayBuilder projectArrayBuilder = Json.createArrayBuilder();
        for (final Project project : projects) {
            projectArrayBuilder.add(getJsonFromProject(project));
        }
        resultBuilder.add("projects", projectArrayBuilder);

        final List<User> users = getController().getUserService().getUsers();
        final JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
        for (final User user : users) {
            userArrayBuilder.add(getJsonFromUser(user));
        }
        resultBuilder.add("users", userArrayBuilder);

        final List<Assignee> assignees = getController().getAssigneeService().findAllAssignee();
        final JsonArrayBuilder assigneeArrayBuilder = Json.createArrayBuilder();
        for (final Assignee assignee : assignees) {
            assigneeArrayBuilder.add(getJsonFromAssignee(assignee));
        }
        resultBuilder.add("assignees", assigneeArrayBuilder);

        final List<Task> tasks = getController().getTaskService().findTaskAll();
        final JsonArrayBuilder taskArrayBuilder = Json.createArrayBuilder();
        for (final Task task : tasks) {
            taskArrayBuilder.add(getJsonFromTask(task));
        }
        resultBuilder.add("tasks", taskArrayBuilder);

        final FileWriter fw = new FileWriter("dataJson/AppDataJson.txt");
        lastBuilder.add("data", resultBuilder);
        fw.write(lastBuilder.build().toString());
        fw.close();
    }

    private JsonObjectBuilder getJsonFromTask(final Task task) {
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id", task.getId());
        objectBuilder.add("projectId", task.getProjectId());
        objectBuilder.add("content", task.getContent());
        objectBuilder.add("priority", task.getPriority());
        objectBuilder.add("term", task.getTerm().toString());
        objectBuilder.add("status", task.getStatus().toString());
        return objectBuilder;
    }

    private JsonObjectBuilder getJsonFromAssignee(final Assignee assignee) {
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id", assignee.getId());
        objectBuilder.add("userId", assignee.getAssignerId());
        objectBuilder.add("taskId", assignee.getTaskId());
        return objectBuilder;
    }

    private JsonObjectBuilder getJsonFromUser(final User user) {
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id", user.getId());
        objectBuilder.add("surname", user.getSurname());
        objectBuilder.add("name", user.getName() == null ? "" : user.getName());
        objectBuilder.add("lastName", user.getLastName() == null ? "" : user.getLastName());
        objectBuilder.add("login", user.getLogin());
        objectBuilder.add("password", user.getPassword());
        objectBuilder.add("role", user.getRole().toString());
        return objectBuilder;
    }

    private JsonObjectBuilder getJsonFromProject(final Project project) {
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id", project.getId());
        objectBuilder.add("authorId", project.getAuthorId());
        objectBuilder.add("name", project.getName());
        return objectBuilder;
    }

}
