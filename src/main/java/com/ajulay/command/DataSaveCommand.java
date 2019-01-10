package com.ajulay.command;

import com.ajulay.entity.Domain;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataSaveCommand extends AbstractCommand {

    public static final String COMMAND = "/save";

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
        final Path dir = Paths.get("data");
        final Path path = Paths.get(dir.toString(), "AppData.txt");
        if (path.toFile().exists()) Files.delete(path);
        if (dir.toFile().exists()) Files.delete(dir);
        Files.createDirectories(dir);
        Files.createFile(path);
        final OutputStream out = new FileOutputStream(path.toFile());
        final Domain domain = getController().createDomain();
        final ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(domain);
        oos.close();
        out.close();
    }

}
