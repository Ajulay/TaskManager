package com.ajulay.command;

import com.ajulay.dto.Domain;
import org.codehaus.jackson.map.ObjectMapper;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DataLoadJsonCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/ljson";
    }

    @Override
    public String getDescription() {
        return "load json data";
    }

    @Override
    public void execute() throws Exception {
        final Path path = Paths.get("datajson/AppDataJson.txt");
        final ObjectMapper mapper = new ObjectMapper();
        final Domain domain = mapper.getJsonFactory().createJsonParser(path.toFile()).readValueAs(Domain.class);
        Domain.loadDomain(domain, getController());
    }

}
