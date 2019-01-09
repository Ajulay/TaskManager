package com.ajulay.command;

import com.ajulay.entity.Domain;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataLoadXmlCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/lxml";
    }

    @Override
    public String getDescription() {
        return "load xml data";
    }

    @Override
    public void execute() throws Exception {
        final Path path = Paths.get("dataxml/AppDataxml.txt");
        final XmlMapper mapper = new XmlMapper();
        FileReader fr = new FileReader(path.toFile());
        final Domain domain = mapper.readValue(fr, Domain.class);
        getController().loadDomain(domain);
    }

}
