package com.ajulay.command;

import com.ajulay.entity.User;
import com.ajulay.enumirated.Role;

import java.util.List;
import java.util.stream.Collectors;

public class UserFindAllCommand extends AbstractCommand {

    @Override
    public String getCommandKeyWord() {
        return "/exs";
    }

    @Override
    public String getDescription() {
        return "show all executors";
    }

    @Override
    public void execute() {
        int index = 1;
        List<User> assigners = getController().getUserService().getUsers();
        if (Role.MANAGER.equals(getController().getCurrentUser().getRole())) {
            assigners = assigners.stream().filter(u -> !u.getRole().equals(Role.ADMIN)).collect(Collectors.toList());
        }
        for (User assigner : assigners) {
            System.out.println(index++ + ". Surname: " + assigner.getSurname() + ", id: " + assigner.getId() + ", role: " +
                    assigner.getRole());
        }
    }

}
