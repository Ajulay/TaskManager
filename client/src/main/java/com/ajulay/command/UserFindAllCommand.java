package com.ajulay.command;

import com.ajulay.endpoint.Session;
import com.ajulay.endpoint.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.event.Observes;
import java.util.List;

public class UserFindAllCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/exs";
    }

    @Override
    public String getDescription() {
        return "show all executors";
    }

    public void execute(@Observes UserFindAllCommand userFindAllCommand) {
        @Nullable final Session session = getController().getCurrentSession();
        if (session == null) {
            System.out.println("You are not login.");
            toBegin();
            return;
        }
        int index = 1;
        @NotNull final List<User> users = getController().getUserService().getUserSoapEndPointPort().getUsers(session);
        for (@NotNull final User user : users) {
            System.out.println(index++ + ". Surname: " + user.getSurname() + ", id: " + user.getId() + ", role: " +
                    user.getRole());
        }
        System.out.println("[Ok]");
        toBegin();
    }

}
