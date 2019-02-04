package com.ajulay.command;

import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.Success;
import com.ajulay.endpoint.User;
import org.jetbrains.annotations.Nullable;

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
        @Nullable final Session session = getController().getCurrentSession();
        if (session == null) {
            System.out.println("You are not login.");
            toBegin();
            return;
        }
        @Nullable final User user = getController().getUserService().getUserSoapEndPointPort().findById(session, session.getUserId());
        if (user == null) {
            System.out.println("Something wrong...");
            toBegin();
            return;
        }
        System.out.println("Enter surname:");
        final String sirname = getController().nextLine();
        if (sirname == null || sirname.isEmpty()) {
            System.out.println("Incorrect data. Try again.");
            toBegin();
            return;
        }
        user.setSurname(sirname);
        @Nullable final Success success = getController().getUserService().getUserSoapEndPointPort().updateUser(session, user);
        if (success == null) {
            System.out.println("No update.");
            toBegin();
            return;
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
