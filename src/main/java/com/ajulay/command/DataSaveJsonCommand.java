package com.ajulay.command;

import com.ajulay.entity.Domain;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        final Path dir = Paths.get("datajson");
        final Path path = Paths.get(dir.toString(), "AppDataJson.txt");
        if (path.toFile().exists()) Files.delete(path);
        if (dir.toFile().exists()) Files.delete(dir);
        Files.createDirectories(dir);
        Files.createFile(path);
        final ObjectMapper mapper = new ObjectMapper();
        final FileWriter fw = new FileWriter(path.toFile());
        final Domain domain = getController().createDomain();
        JsonGenerator jsonGenerator = mapper.getJsonFactory().createJsonGenerator(fw);
        jsonGenerator.writeObject(domain);
        jsonGenerator.close();
        fw.close();
    }

}
