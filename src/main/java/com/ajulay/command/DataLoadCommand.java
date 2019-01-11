package com.ajulay.command;

import com.ajulay.dto.Domain;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DataLoadCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/load";
    }

    @Override
    public String getDescription() {
        return "load data";
    }

    @Override
    public void execute() throws Exception {
        final FileInputStream fis = new FileInputStream("data/AppData.txt");
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final Domain domain = (Domain) ois.readObject();
        Domain.loadDomain(domain, getController());
        ois.close();
        fis.close();
    }

}
