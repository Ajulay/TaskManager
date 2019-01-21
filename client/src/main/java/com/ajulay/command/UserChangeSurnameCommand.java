package com.ajulay.command;

import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.User;

public class UserChangeSurnameCommand extends AbstractCommand {
    @Override
    public String getCommandKeyWord() {
        return "/snm";
    }

    @Override
    public String getDescription() {
        return "change surname";
    }

    @Override
    public void execute() {
        final Session session = getController().getCurrentSession();
        if (session == null) {
            System.out.println("You are not login.");
            return;
        }
        final User user = getController().getUserService().getUserSoapEndPointPort().findById(session, session.getUserId());
        System.out.println("Enter surname:");
        final String sirname = getController().nextLine();
        if (sirname == null || sirname.isEmpty()) {
            System.out.println("Incorrect data. Try again.");
            return;
        }
        user.setSurname(sirname);
        getController().getUserService().getUserSoapEndPointPort().updateUser(session, user);
        System.out.println("Surname set.");
    }

}
