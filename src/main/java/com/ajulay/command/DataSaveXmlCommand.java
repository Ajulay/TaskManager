package com.ajulay.command;

import com.ajulay.entity.Domain;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataSaveXmlCommand extends AbstractCommand {

    public static final String COMMAND = "/sxml";

    @Override
    public String getCommandKeyWord() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return "save current data to xml";
    }

    @Override
    public void execute() throws Exception {
        final Path dir = Paths.get("dataxml");
        final Path path = Paths.get(dir.toString(), "AppDataXml.txt");
        if (path.toFile().exists()) Files.delete(path);
        if (dir.toFile().exists()) Files.delete(dir);
        Files.createDirectories(dir);
        Files.createFile(path);
        final XmlMapper mapper = new XmlMapper();
        final FileWriter fw = new FileWriter(path.toFile());
        final Domain domain = getController().createDomain();
        final ToXmlGenerator generator = mapper.getFactory().createGenerator(fw);
        generator.writeObject(domain);
        generator.close();
        fw.close();
    }

}
