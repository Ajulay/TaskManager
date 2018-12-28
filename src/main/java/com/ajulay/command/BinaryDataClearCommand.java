package com.ajulay.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinaryDataClearCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/clr";
    }

    @Override
    public String getDescription() {
        return "clear binary data";
    }

    @Override
    public void execute() throws Exception {
        final Path path = Paths.get("data/AppData.txt");
        final Path dir = Paths.get("data");
        if (path.toFile().exists()) {
            Files.delete(path);
            Files.delete(dir);

        }

    }

}
