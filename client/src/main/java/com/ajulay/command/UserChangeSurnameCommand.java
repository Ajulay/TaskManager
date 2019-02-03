package com.ajulay.command;

import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.User;

import javax.enterprise.event.Observes;

public class UserChangeSurnameCommand extends AbstractCommand {
    @Override
    public String getCommandKeyWord() {
        return "/snm";
    }

    @Override
    public String getDescription() {
        return "change surname";
    }

    public void execute(@Observes UserChangeSurnameCommand userChangeSurnameCommand) {
        final Session session = getController().getCurrentSession();
        if (session == null) {
            System.out.println("You are not login.");
            toBegin();
            return;
        }
        final User user = getController().getUserService().getUserSoapEndPointPort().findById(session, session.getUserId());
        System.out.println("Enter surname:");
        final String sirname = getController().nextLine();
        if (sirname == null || sirname.isEmpty()) {
            System.out.println("Incorrect data. Try again.");
            toBegin();
        }
        user.setSurname(sirname);
        getController().getUserService().getUserSoapEndPointPort().updateUser(session, user);
        System.out.println("Surname set.");
        System.out.println("[Ok]");
        toBegin();
    }

}
